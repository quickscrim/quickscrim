package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
