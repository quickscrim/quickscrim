package com.quickscrim.controllers;

import com.quickscrim.models.Category;
import com.quickscrim.models.Event;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.UserRepository;
import com.quickscrim.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final CategoryRepository categoryDao;
    private final UserService userService;

    public EventController(EventRepository eventDao, UserRepository userDao, CategoryRepository categoryDao, UserService userService){
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.userService = userService;
    }

    @GetMapping ("/events/index")
    public String returnEventsIndex(Model model) {
        model.addAttribute("event", eventDao.findAll());
        return "events/index";
    }

    @GetMapping("/events/create")
    public String create(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categoryDao.findAll());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String insertEvent(@ModelAttribute @Valid Event eventPosted, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            return "events/index";
        }
        User sessionUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userDb = userDao.findOne(sessionUser.getId());

        eventPosted.setEventCreator(userDb);
        eventDao.save(eventPosted);
        return "redirect:/home";
    }

    @GetMapping("/events/{id}/edit")
    public  String editEvent(@PathVariable Long id, Model model) {
        Event event = eventDao.findOne(id);
        if (!userService.isOwner(event.getEventCreator())) {
            return "redirect:/events";
        }
        model.addAttribute("event", event);
        return "events/edit";
    }

    @PostMapping("/events/edit")
    public String updateEvent(@Valid Event eventEdited, Errors val, Model model) {
        if (val.hasErrors()) {
            model.addAttribute("errors", val);
            model.addAttribute("event", eventEdited);
            return "events/edit";
        }
        Event eventUpdated = eventDao.findOne(eventEdited.getId());
        eventDao.save(eventUpdated);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventDao.delete(id);
        return "redirect:/events";
    }

}
