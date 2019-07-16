package com.quickscrim.controllers;

import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ForumController {

    private final PostService postService;
//    private final UserService userService;

    public ForumController(PostService postService) {
        this.postService = postService;
//        this.userService = userService;
    }

    @GetMapping("forum/select")
    public String CategorySelect() {
        return "forum/category";
    }

    @GetMapping("forum/posts/{category}")
        public String Posts(@PathVariable String category, Model model) {
        model.addAttribute("category", category);
        return "showposts";
    }

    @GetMapping("forum/posts/create")
    public String getCreate(Model model) {
        model.addAttribute("post", new Post());
        return "forum/create";
    }

    @PostMapping("forum/posts/create")
    public String postCreate(@Valid Post post, Errors validation, Model model) {

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        User authedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setPostAuthor(authedUser);
        postService.save(post);
        return "redirect:/posts";
    }

}