
package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.PostRepository;
import com.quickscrim.repositories.UserRepository;
import com.quickscrim.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {
    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CategoryRepository categoryDao;
    private final UserService userService;

    public HomeController(EventRepository eventDao, UserRepository userDao, PostRepository postDao, CategoryRepository categoryDao, UserService userService) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.postDao = postDao;
        this.categoryDao = categoryDao;
        this.userService = userService;
    }


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();

        // if not logged in:
        if (token instanceof AnonymousAuthenticationToken) return "test";

        // if logged in, redirect
        return String.format("redirect:%s", "/home");
    }


//    Landing Page after a User sucessfully logs in

    @GetMapping("/home")
    public String usersHome(Model model, @RequestParam(name="categories", required = false) Long id) {
        User logUser = userService.loggedInUser();
        if (logUser == null) {
            model.addAttribute("msg", "You need to be logged in to be able to see");
            return "/test";
        }

        List<Event> events;
        if(id!=null){
        events = eventDao.findAllByEventSport_Id(id);
        } else{
           events = eventDao.findAllByEventCreator(logUser);
        }
        List<Event>joinedEvents = logUser.getUserEvents();
        events.addAll(joinedEvents);

        model.addAttribute("search", eventDao.findAllByEventSport_Id(id));
        model.addAttribute("events", events);
        model.addAttribute("posts", postDao.findAllByPostByUser(logUser));
        model.addAttribute("categories", categoryDao.findAll());
        return "user/index";
    }
}
