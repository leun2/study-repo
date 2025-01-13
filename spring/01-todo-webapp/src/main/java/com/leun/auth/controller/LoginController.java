package com.leun.auth.controller;

import com.leun.auth.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
@RequestMapping("/login")
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String postlogin(@RequestParam String name, @RequestParam String password,
        ModelMap modelMap) {

        if (authenticationService.authenticate(name, password)) {
            modelMap.put("name", name);
            modelMap.put("password", password);
            return "index";
        }

        System.out.println(name + " " + password);
        return "login";
    }
}
