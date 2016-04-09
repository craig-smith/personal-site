package com.craig.security;

import com.craig.entity.user.User;
import com.craig.entity.user.event.AccountCreatedEvent;
import com.craig.entity.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Craig on 3/12/2016.
 */
@Component
public class AccountListeners {

    private static Logger logger = Logger.getLogger(AccountListeners.class);

    @Autowired
    UserService userService;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event){
        logger.debug("Authentication failed for: " + event.getAuthentication().getPrincipal());
        String username = (String) event.getAuthentication().getPrincipal();

        User user = userService.findByUserName(username);

        if(user != null) {
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
    public void accountCreatedListener(AccountCreatedEvent event){
        logger.debug(event.getUser().getEmail());
    }
}
