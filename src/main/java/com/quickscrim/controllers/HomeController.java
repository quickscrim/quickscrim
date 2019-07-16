package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


//    Landing Page for Users

    @GetMapping("/")
    public String index() {
<<<<<<< HEAD

        return "home";

=======
        return "index";
>>>>>>> origin/master
    }


//    Landing Page after a User sucessfully loggs in

    @GetMapping("/home")
    public String homePageforUsers(){
        return "user/home";
    }

}