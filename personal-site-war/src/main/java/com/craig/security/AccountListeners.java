package com.craig.security;

import com.craig.email.EmailObj;
import com.craig.email.EmailObjBuilder;
import com.craig.email.iface.EmailSender;
import com.craig.entity.user.User;
import com.craig.entity.user.event.AccountCreatedEvent;
import com.craig.entity.user.service.UserActionService;
import com.craig.entity.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Craig on 3/12/2016.
 */
@Component
public class AccountListeners {

    private static Logger logger = Logger.getLogger(AccountListeners.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private UserActionService userActionService;

    @Value("${com.craig.domain.name}")
    private String domainName;

    @Value("${com.craig.port:8080}")
    private String port;

    private String link = "http://" + domainName + port;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        logger.debug("Authentication failed for: " + event.getAuthentication().getPrincipal());
        String username = (String) event.getAuthentication().getPrincipal();

        User user = userService.findByUserName(username);

        if (user != null) {
            int loginAttempts = user.getLoginAttempts();
            if (loginAttempts > 5) {
                user.setLoginAttempts(0);
                user.setEnabled(false);
                logger.debug("Locking account for user: " + user.toString());
            } else {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
            }
            userService.updateDetails(user);
        }
    }

    @EventListener
    public void accountCreatedListener(AccountCreatedEvent event) {
        logger.debug(event.getUser().getEmail());
        String token = userActionService.setActivateAccount(event.getUser());
        String userActivationLink = link + "/" + "activate" + "?token=" + token;

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("user", event.getUser().getUserName());
        body.put("activation_link", userActivationLink);

        EmailObj emailObj = new EmailObjBuilder()//
                .from("accounts@craig.com")//
                .template("/new_user.vm")//
                .to(event.getUser().getEmail())//
                .subject("New Account")//
                .body(body)//
                .createEmailObj();

        emailSender.sendMail(emailObj);
    }
}
