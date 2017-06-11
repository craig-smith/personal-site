package com.craig.controller;


import com.craig.aspects.userlinks.AddUserLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Craig on 3/11/2016.
 */
@Controller
public class MainController {


    @AddUserLink
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model){
        return "index";
    }
}
