package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileStackController {

    @GetMapping ("/upload/pic")
    public String displayPickerPage () {
        return "filestacktest";
    }

    @PostMapping ("/upload/pic")
    public String postProfilePic(@RequestParam("profilePicUrl") String profilePicUrl) {
//        find user, set user pic attribute to profilePicUrl

        return "redirect:user/profile";
    }
}
