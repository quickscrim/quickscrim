package com.quickscrim.controllers;

import com.quickscrim.models.Category;
import com.quickscrim.models.Event;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
import com.quickscrim.repositories.PostRepository;
import com.quickscrim.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CategoryRepository categoryDao;

//    private final UserService userService;

    public PostController(PostService postService, PostRepository postRepository, CategoryRepository categoryDao) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.categoryDao = categoryDao;
//        this.userService = userService;
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
        model.addAttribute("categories", categoryDao.findAll());
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

    @GetMapping("/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        User authorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authorUser.getId() == postService.getPost(id).getPostAuthor().getId()) {
            return "posts/edit";
        } else return "redirect/posts{id}";
    }

    @PostMapping("/posts/{id}/edit")
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


    @GetMapping("/posts/filter")
    public String usersHome(Model model, @RequestParam(name="categories", required = false) Long id) {
        Iterable<Post> posts;
        if(id!=null){
        posts = postRepository.findAllByPostCategory_Id(id);
        } else{
        posts = postService.getAllPosts();
        }
        model.addAttribute("filter", postRepository.findAllByPostCategory_Id(id));
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categoryDao.findAll());
        return "posts/index";
    }

    @PostMapping("/posts/search")
    public String search(@RequestParam(name = "term") String term, Model model){
        term = "%"+term+"%";
        model.addAttribute("posts", postRepository.findByBodyIsLikeOrTitleIsLike(term, term));
        return "posts/index";
    }

}