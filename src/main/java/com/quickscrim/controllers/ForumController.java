package com.quickscrim.controllers;

import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/posts/select")
    public String CategorySelect() {
        return "posts/category";
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        try {
            model.addAttribute("post", postService.getPost(id));
        } catch (IndexOutOfBoundsException e) {
            model.addAttribute("post", new Post());
        }
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreate(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@Valid Post post, Errors validation, Model model) {

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        User authorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setPostAuthor(authorUser);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("forum/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "posts/edit";
    }

    @PostMapping("forum/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts/{id}";
    }

    @PostMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        User authorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authorUser.getId() == postService.getPost(id).getPostAuthor().getId())
            postService.delete(id);
        return "redirect:/posts";
    }

}