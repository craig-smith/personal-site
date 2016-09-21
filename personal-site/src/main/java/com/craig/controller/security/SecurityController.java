package com.craig.controller.security;


import com.craig.aspects.userlinks.AddUserLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Craig on 2/29/2016.
 */
@Controller
public class SecurityController {




    @AddUserLink
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "login";
    }

}
