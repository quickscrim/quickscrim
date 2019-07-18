package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.models.User;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventDao;
    private final UserRepository userDao;

    public EventController(EventRepository eventDao, UserRepository userDao){
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    @GetMapping("/events/create")
    public String create(Model model){
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String insertEvent(@ModelAttribute @Valid Event eventPosted, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            eventPosted.setUserEvents((List<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            eventDao.save(eventPosted);
            return "events/index";
        }
        return "redirect:/home";
    }

}
