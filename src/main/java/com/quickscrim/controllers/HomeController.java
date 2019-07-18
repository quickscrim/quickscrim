
package com.quickscrim.controllers;

import com.quickscrim.models.Event;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.repositories.EventRepository;
import com.quickscrim.repositories.PostRepository;
import com.quickscrim.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class HomeController {
    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final PostRepository postDao;

    public HomeController(EventRepository eventDao, UserRepository userDao, PostRepository postDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
        return "index";
    }


//    Landing Page after a User sucessfully logs in

    @GetMapping("/home")
    public String homePageforUsers(Model model) {
        model.addAttribute("events", eventDao.findAll());
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("event", new Event());
        model.addAttribute("post", new Post());
        return "user/home";
    }
}
