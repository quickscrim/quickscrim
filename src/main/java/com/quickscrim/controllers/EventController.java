package com.quickscrim.controllers;

import com.quickscrim.models.Category;
import com.quickscrim.models.Event;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
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
    private final CategoryRepository categoryDao;

    public EventController(EventRepository eventDao, UserRepository userDao, CategoryRepository categoryDao){
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping ("events/index")
    public String returnEventsIndex() {
        return "events/index";
    }

    @GetMapping("/events/create")
    public String create(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categoryDao.findAll());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String insertEvent(@ModelAttribute @Valid Event eventPosted, Errors validation, Model model, Category category) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            return "events/index";
        }
        User sessionUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userDb = userDao.findOne(sessionUser.getId());

        eventPosted.setEventCreator(userDb);
        eventPosted.setEventSport(category);
        eventDao.save(eventPosted);
        return "redirect:/home";
    }

}
