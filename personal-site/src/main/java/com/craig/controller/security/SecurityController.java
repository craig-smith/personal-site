package com.craig.controller.security;


import com.craig.entity.user.service.UserService;
import com.craig.entity.userlinks.service.UserLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Craig on 2/29/2016.
 */
@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLinksService userLinksService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        model.addAttribute("links", userLinksService.getUserLinks());
        return "login";
    }

}
