package com.quickscrim.controllers;

import com.quickscrim.models.User;
import com.quickscrim.repositories.UserRepository;
import com.quickscrim.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileStackController {
    private final UserService userService;
    private UserRepository userDao;

    public FileStackController(UserRepository userDao,UserService userService) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping ("/upload/pic")
    public String displayPickerPage () {
        return "filestacktest";
    }

    @PostMapping ("/upload/pic")
    public String postProfilePic(@RequestParam("profilePicUrl") String profilePicUrl) {
//        find user, set user pic attribute to profilePicUrl
        System.out.println(profilePicUrl);
        User logUser = userService.loggedInUser();

        logUser.setImage(profilePicUrl);

        userDao.save(logUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ((User)auth.getPrincipal()).setImage(profilePicUrl);

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);


        return "redirect:/profile/"+logUser.getId();
    }
}
