package com.craig.controller.userlinks;


import com.craig.entity.userlinks.UserLink;
import com.craig.entity.userlinks.service.UserLinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Craig on 2/24/2016.
 */
@Controller
public class LinksController {

    @Autowired
    private UserLinksService userLinksService;

    @RequestMapping(value = "admin/allLinks", method = RequestMethod.GET)
    public String getAllLinks(Model model) {
        model.addAttribute("links", userLinksService.getUserLinks());
        model.addAttribute("allLinks", userLinksService.getAllLinks());

        return "admin/allLinks";
    }

    @RequestMapping(value = "admin/editLink", method = RequestMethod.GET)
    public String getEditLink(Model model, @RequestParam Long id) {
        model.addAttribute("links", userLinksService.getUserLinks());
        model.addAttribute("link", userLinksService.findById(id));

        return "admin/editLink";
    }

    @RequestMapping(value = "admin/editLink", method = RequestMethod.POST)
    public String postEditLink(Model model, @RequestParam Long id, @RequestParam String url, @RequestParam String acl,
                               @RequestParam String name, @RequestParam String description) {

        model.addAttribute("links", userLinksService.getUserLinks());
        UserLink link = userLinksService.findById(id);
        link.setName(name);
        link.setAcl(acl);
        link.setUrl(url);
        link.setDescription(description);

        userLinksService.save(link);

        return "redirect:allLinks";
    }

    @RequestMapping(value = "admin/deleteLink", method = RequestMethod.POST)
    public String postDeleteLink(Model model, @RequestParam Long id) {
        model.addAttribute("links", userLinksService.getUserLinks());
        userLinksService.deleteById(id);

        return "redirect:allLinks";
    }

    @RequestMapping(value = "admin/addLink", method = RequestMethod.POST)
    public String postAddLink(Model model, @RequestParam String url, @RequestParam String acl,
                              @RequestParam String name, @RequestParam String description) {
        model.addAttribute("links", userLinksService.getUserLinks());
        UserLink link = new UserLink(url, acl, name, description);
        userLinksService.save(link);
        return "redirect:allLinks";
    }

    @RequestMapping(value = "admin/addLink", method = RequestMethod.GET)
    public String getAddLink(Model model) {
        model.addAttribute("links", userLinksService.getUserLinks());
        return "admin/addLink";
    }

}
