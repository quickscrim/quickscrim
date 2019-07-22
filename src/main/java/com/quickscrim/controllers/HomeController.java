
package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.PostRepository;
import com.quickscrim.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class HomeController {
    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CategoryRepository categoryDao;

    public HomeController(EventRepository eventDao, UserRepository userDao, PostRepository postDao, CategoryRepository categoryDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.postDao = postDao;
        this.categoryDao = categoryDao;
    }


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();

        // if not logged in:
        if (token instanceof AnonymousAuthenticationToken) return "index";

        // if logged in, redirect
        return String.format("redirect:%s", "/home");
    }


//    Landing Page after a User sucessfully logs in

    @GetMapping("/home")
    public String homePageforUsers(Model model) {
        User sessionUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userDb = userDao.findOne(sessionUser.getId());
        model.addAttribute("events", eventDao.findAllByEventCreator();
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("event", new Event());
        model.addAttribute("post", new Post());
        return "user/home";
    }
}
