package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
<<<<<<< HEAD
        return "test";
=======
        return "home";
>>>>>>> 20a2a36c048abfae5dd04abb20443328d2844a24
    }
}