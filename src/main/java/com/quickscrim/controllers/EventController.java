package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    private final EventRepository eventDao;
    private final UserRepository userDao;

    public EventController(EventRepository eventDao, UserRepository userDao){
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

//
//    @GetMapping("/home")
//    public String createPostForm(Model model){
//        model.addAttribute("event", new Event());
//        return "user/home";
//    }


}
