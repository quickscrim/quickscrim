package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ForumController {

    @GetMapping("forum/select")
    public String CategorySelect() {
        return "forum/category";
    }

    @GetMapping("forum/posts/{category}")
        public String Posts(@PathVariable String category, Model model) {
        model.addAttribute("category", category);
        return "showposts";
    }

    @GetMapping("forum/post/create")
    public String creatPost() {
        return "createpost";
    }

}