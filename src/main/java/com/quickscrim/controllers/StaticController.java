package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("pages/about")
    public String about() {
        return "pages/about";
    }

    @GetMapping("pages/privacy")
    public String privacy() {
        return "pages/privacy";
    }

    @GetMapping("pages/terms")
    public String terms () {
        return "pages/terms";
    }


}
