package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
        return "index";
    }


//    Landing Page after a User sucessfully logs in

    @GetMapping("/home")
    public String homePageforUsers(){
        return "user/home";
    }

}