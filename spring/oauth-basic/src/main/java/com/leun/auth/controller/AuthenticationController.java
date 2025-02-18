package com.leun.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/")
    public String getAuthentication(Authentication authentication) {
        System.out.println(authentication);
        return authentication.getName();
    }
}
