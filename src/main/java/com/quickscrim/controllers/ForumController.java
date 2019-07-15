package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForumController {

    @GetMapping("/forum/category")
    public String CategorySelect() {
        return "forum/category";
    }

    @GetMapping("/forum/posts")
        public String Posts() {
        return "forum/posts";
    }





}
