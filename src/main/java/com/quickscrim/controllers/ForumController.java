package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {

    @GetMapping("forum/select")
    public String CategorySelect() {
        return "forum/category";
    }

    @GetMapping("forum/posts{category}")
        public String Posts() {
        return "forum/posts";
    }

}
