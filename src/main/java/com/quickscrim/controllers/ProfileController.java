package com.quickscrim.controllers;

import com.quickscrim.models.User;
import com.quickscrim.repositories.UserRepository;
import com.quickscrim.services.UserDetailsLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    private UserRepository users;
    private UserDetailsLoader userService;

public ProfileController(UserRepository users, UserDetailsLoader userService) {
    this.users = users;
    this.userService = userService;
}

    @GetMapping("/users")
    public String profileIndex(Model model) {
        model.addAttribute("profile", userService.getAllUsers());
        return "profile/index";
    }

    @GetMapping("/profile/{id}")
    public String displayUserProfile(
        @PathVariable long id,
        Model model
    ) {
        model.addAttribute("profile", userService.getUser(id));
        return "profile/profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String editProfile(
        @PathVariable long id,
        Model model
    ) {
        model.addAttribute("profile", userService.getUser(id));
        return "profile/edit";
    }

    @PostMapping("/profile/{id}/edit")
    public String postEditProfile(
    @PathVariable long id,
    @ModelAttribute User user
    ) {
        userService.save(user);
        return "redirect:/profile/{id}";
    }

}