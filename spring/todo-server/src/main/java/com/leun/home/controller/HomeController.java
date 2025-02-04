package com.leun.home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HomeController {

    @GetMapping("/token")
    public ResponseEntity<String> tokenCheck() {
        return new ResponseEntity<>("token check success", HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<String> getHome() {
        return new ResponseEntity<>("Home", HttpStatus.OK);
    }

    @GetMapping("/home/{user-name}")
    public ResponseEntity<String> getHomeByUserName(@PathVariable("user-name") String userName) {
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }
}
