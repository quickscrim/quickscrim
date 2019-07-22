package com.quickscrim.controllers;

import com.quickscrim.models.User;
import com.quickscrim.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileStackController {
    private final UserService userService;

    public FileStackController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/upload/pic")
    public String displayPickerPage () {
        return "filestacktest";
    }

    @PostMapping ("/upload/pic")
    public String postProfilePic(@RequestParam("profilePicUrl") String profilePicUrl) {
//        find user, set user pic attribute to profilePicUrl
        User logUser = userService.loggedInUser();


        return "redirect:user/profile";
    }
}
