package com.craig.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.craig.entity.user.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Created by Craig on 4/9/2016.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        com.craig.entity.user.User dbUser = userService.findByUserName(authUser.getUsername());

        dbUser.setLoginAttempts(0);
        userService.updateDetails(dbUser);

       redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/index");
    }
}
