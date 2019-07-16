
package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final EventRepository eventDao;
    private final UserRepository userDao;

    public HomeController(EventRepository eventDao, UserRepository userDao){
        this.eventDao = eventDao;
        this.userDao = userDao;
    }


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
        return "index";
    }


//    Landing Page after a User sucessfully logs in

    @GetMapping("/home")
    public String homePageforUsers(Model model){
        model.addAttribute("events", eventDao.findAll());
        model.addAttribute("event", new Event());
        return "user/home";
    }

}
