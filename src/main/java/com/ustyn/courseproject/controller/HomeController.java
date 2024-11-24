package com.ustyn.courseproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // TODO: change tables dropdown to contain mappings for every table
    // TODO: create queries dropdown to contain mappings for every query
    @GetMapping("/home")
    public String home() {
        return "home/home-page";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "home/about-us-page";
    }
}
