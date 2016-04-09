package com.craig.controller;

import com.craig.entity.userlinks.service.UserLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Craig on 3/11/2016.
 */
@Controller
public class MainController {

    @Autowired
    UserLinksService userLinksService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute("links", userLinksService.getUserLinks());
        return "index";
    }
}
