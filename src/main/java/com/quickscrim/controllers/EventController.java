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

    @GetMapping("/events/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventDao.findOne(id));
        return "events/show";
    }

    @GetMapping("/events/create")
    public String create(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categoryDao.findAll());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String insertEvent(@ModelAttribute @Valid Event eventPosted, Errors validation, Model model) {
        User logUser = userService.loggedInUser();
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            return "events/index";
        }
        eventPosted.setEventCreator(logUser);
        eventDao.save(eventPosted);
        return "redirect:/home";
    }

    @GetMapping("/events/{id}/edit")
    public  String editEvent(@PathVariable Long id, Model model) {
        Event event = eventDao.findOne(id);
        if (!userService.isOwner(event.getEventCreator())) {
            return "redirect:/events/edit";
        }
        model.addAttribute("event", event);
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String updateEvent(@PathVariable Long id,@Valid Event eventEdited, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("event", eventEdited);
            return "events/edit";
        }
        Event eventUpdated = eventDao.findOne(eventEdited.getId());
        eventDao.save(eventUpdated);
        return "redirect:/home";
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventDao.delete(id);
        return "redirect:/home";
    }

}
