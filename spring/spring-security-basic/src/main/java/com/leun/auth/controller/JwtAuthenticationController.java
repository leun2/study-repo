package com.leun.auth.controller;

import com.leun.auth.record.JwtResponse;
import com.leun.auth.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class JwtAuthenticationController {

    private final JwtService jwtService;

    public JwtAuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/authentication")
    public JwtResponse authenticated(Authentication authentication) {

        String token = jwtService.createToken(authentication);

        return new JwtResponse(token);
    }
}
