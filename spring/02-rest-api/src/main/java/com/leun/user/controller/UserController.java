package com.leun.user.controller;

import com.leun.user.dto.UserDto;
import com.leun.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user-id") Integer userId) {
        UserDto user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
