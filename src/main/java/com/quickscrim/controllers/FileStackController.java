package com.quickscrim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileStackController {

    @GetMapping ("/upload/pic")
    public String displayPickerPage () {
        return "filestacktest";
    }
}
