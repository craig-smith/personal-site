package com.craig.controller.user;


import com.craig.aspects.userlinks.AddUserLink;
import com.craig.autoregister.AutoRegisterLink;
import com.craig.autoregister.AutoRegisterLinks;
import com.craig.base.error.ErrorMessage;
import com.craig.entity.user.Action;
import com.craig.entity.user.User;
import com.craig.entity.user.UserAction;
import com.craig.entity.user.UserRole;
import com.craig.entity.user.service.UserActionService;
import com.craig.entity.user.service.UserService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Craig on 3/6/2016.
 */

@AutoRegisterLinks(name = "UserLinksController", autoregisterLinks = {
        @AutoRegisterLink(name = "Log In", url = "/login", acl = "ROLE_ANONYMOUS", description = "Log In"),
        @AutoRegisterLink(name = "Create Account",url = "/createAccount", acl = "ROLE_ANONYMOUS" , description = "Create your account today!"),
        @AutoRegisterLink(name = "Update Account", url = "/user/updateAccount", acl = "ROLE_USER", description = "Update Account Details"),
        @AutoRegisterLink(name = "Admin User List", url = "/admin/userList", acl = "ROLE_ADMIN", description = "Admin User List")})
@Controller
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserActionService userActionService;


    @AddUserLink
    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String getCreateAccount(Model model) {
        logger.debug("getCreateAccount method");

        return "createAccount";
    }

    @AddUserLink
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String postCreateAccount(@RequestParam String username, @RequestParam String password,
                                    @RequestParam String email, Model model) {
        logger.debug("creating user: " + username + " " + email);
        try {
            User user = userService.createNewUser(username, password, email);
            logger.debug("Account created for user: " + user.toString());
        } catch (DataIntegrityViolationException ex) {
            List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
            errors.add(new ErrorMessage("Try again", "Username already in use."));

            model.addAttribute("errorMsg", errors);
            return "/createAccount";
        } catch (ConstraintViolationException ex) {
            List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
            Set<ConstraintViolation<?>> violationList = ex.getConstraintViolations();
            for (ConstraintViolation violation : violationList) {
                errors.add(new ErrorMessage("Invalid Input", violation.getMessage()));
            }
            model.addAttribute("errorMsg", errors);
            return "/createAccount";
        }

        return "redirect:login";
    }

    @AddUserLink
    @RequestMapping(value = "user/updateAccount", method = RequestMethod.GET)
    public String getUpdateAccount(Model model) {
        logger.debug("getUpdateAccount method");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        model.addAttribute("user", userService.findByUserName(name));
        return "user/updateAccount";
    }

    @AddUserLink
    @RequestMapping(value = "activate", method = RequestMethod.GET)
    public String getActivateAccount(Model model, @RequestParam(name = "token") String token) {
        UserAction userAction = userActionService.getUserfromToken(token);
        DateTime today = new DateTime();

        if (userAction != null && userAction.getAction() == Action.Activate
                && userAction.getValidTill().after(new Timestamp(today.getMillis()))) {
            User user = userAction.getUser();
            user.setEnabled(true);
            userService.updateDetails(user);
            userActionService.deleteUserAction(userAction);
            model.addAttribute("user", user);
            model.addAttribute("valid", true);
        } else {
            model.addAttribute("valid", false);
            List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
            ErrorMessage errorMessage = new ErrorMessage("Validation Error",
                    "Sorry, there was a problem validating your account.");
            errorMessage.setErrorType("Activation Error!");
            errors.add(errorMessage);
            model.addAttribute("errorMsg", errors);
        }
        return "activate";
    }

    @AddUserLink
    @RequestMapping(value = "user/updateAccount", method = RequestMethod.POST)
    public String postUpdateAccount(Model model, @RequestParam String username, @RequestParam String password,
                                    @RequestParam String passwordConfirm, @RequestParam String email) {
        logger.debug("updating account details for: " + username);
        User user = userService.findByUserName(username);
        try {
            user.setEmail(email);
        } catch (ValidationException ex) {
            String errorMsg = "Sorry, there was an error updating your account. \n" +
                    "Make sure your email address is correct.";
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("user", user);
            return "/user/updateAccount";
        }
        if (password != null && !password.equals("")) {
            if (password.equals(passwordConfirm)) {
                logger.debug("changing password for user: " + username);
                userService.changePassword(user, password);
            } else {
                String errorMsg = "Passwords do not match, please try again.";
                model.addAttribute("errorMsg", errorMsg);
                model.addAttribute("user", user);
                return "/user/updateAccount";
            }
        }
        userService.updateDetails(user);

        return "index";
    }

    @AddUserLink
    @RequestMapping(value = "admin/userList", method = RequestMethod.GET)
    public String getAdminUserList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "/admin/userList";

    }

    @AddUserLink
    @RequestMapping(value = "admin/editUser", method = RequestMethod.GET)
    public String getAminEditUser(Model model, @RequestParam String username) {
        model.addAttribute("user", userService.findByUserName(username));
        return "/admin/editUser";
    }

    @RequestMapping(value = "admin/deleteUser", method = RequestMethod.POST)
    public String postAdminDeleteUser(Model model, @RequestParam String username) {
        userService.deleteUser(userService.findByUserName(username));
        return "redirect:userList";
    }

    @AddUserLink
    @RequestMapping(value = "admin/editUser", method = RequestMethod.POST)
    public String postAdminUpdateUser(Model model, @ModelAttribute User user){

        User dbUser = userService.findByUserName(user.getUserName());
        dbUser.setEmail(user.getEmail());
        dbUser.setLoginAttempts(user.getLoginAttempts());
        dbUser.setEnabled(user.isEnabled());

        userService.updateDetails(dbUser);

        return "redirect:userList";
    }

    @AddUserLink
    @RequestMapping(value = "admin/editUserRoles", method = RequestMethod.GET)
    public String getAdminEditUserRoles(Model model, @RequestParam String username){
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);

        List<UserRole.USER_ROLE> availableRoles = Arrays.asList(UserRole.USER_ROLE.values());
        //UserRole.USER_ROLE[] availableRoles = UserRole.USER_ROLE.values();


        model.addAttribute("roles" , user.getUserRoll());
        model.addAttribute("availableRoles", availableRoles);
        return "/admin/editUserRoles";
    }

    @AddUserLink
    @RequestMapping(value = "admin/editUserRoles", method = RequestMethod.POST)
    public String postAdminEditUserRoles(Model model, @RequestParam List<UserRole.USER_ROLE> roles, @RequestParam String username) {
        User user = userService.findByUserName(username);
        Set<UserRole> userRoles = new HashSet<UserRole>();
        for(UserRole.USER_ROLE role : roles) {
            UserRole userRole = new UserRole(role.getRole(), user );
            userRoles.add(userRole);
        }
        user.setUserRoll(userRoles);

        userService.updateDetails(user);

        return "redirect:userList";

    }
}
