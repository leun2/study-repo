package com.leun.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome(ModelMap modelMap) {
        modelMap.put("name", "lee");
        return "home";
    }
}

