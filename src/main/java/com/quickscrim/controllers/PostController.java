package com.quickscrim.controllers;

import com.quickscrim.models.Category;
import com.quickscrim.models.Event;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import com.quickscrim.repositories.CategoryRepository;
import com.quickscrim.repositories.PostRepository;
import com.quickscrim.repositories.UserRepository;
import com.quickscrim.services.PostService;
import com.quickscrim.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;
    private final UserRepository userDao;
    private final PostRepository postRepository;
    private final CategoryRepository categoryDao;
    private final UserService userService;

    public PostController(PostService postService, UserRepository userDao, PostRepository postRepository, CategoryRepository categoryDao, UserService userService) {
        this.postService = postService;
        this.userDao = userDao;
        this.postRepository = postRepository;
        this.categoryDao = categoryDao;
        this.userService = userService;
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
        User logUser = userService.loggedInUser();
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        post.setPostAuthor(logUser);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postRepository.findOne(id);
//        model.addAttribute("post", postService.getPost(id));
//        User authorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.isOwner(post.getPostAuthor())) {
            return "redirect:/posts/edit";
        }
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        post.setPostAuthor(userDao.findOne(postRepository.findOne(id).getPostAuthor().getId()));
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        postRepository.delete(id);
        return "redirect:/posts";
    }

//    @GetMapping("/posts")
//    public String index(Model model) {
//        model.addAttribute("posts", postService.getAllPosts());
//        return "posts/index";
//    }

    @GetMapping("/posts")
    public String PostIndex(Model model, @RequestParam(name="categories", required = false) Long id) {
        Iterable<Post> posts;
        if(id!=null){
        posts = postRepository.findAllByPostCategory_Id(id);
        } else{
        posts = postService.getAllPosts();
        }
        model.addAttribute("posts", postService.getAllPosts());
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