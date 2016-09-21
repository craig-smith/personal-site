package com.craig.aspects.userlinks;

import com.craig.entity.userlinks.service.UserLinksService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Created by craig on 9/19/16.
 */
@Component
@Aspect
public class UserLinkAspect {

    @Autowired
    private UserLinksService userLinksService;



    @Before("@annotation(com.craig.aspects.userlinks.AddUserLink) && args(model, ..)")
    public void AddUserLinks(Model model){
        model.addAttribute("links", userLinksService.getUserLinks());
        System.out.println("in aspect");
    }
}