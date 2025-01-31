package com.leun.user.controller;

import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody UserDto userDto) {
        String userName = userService.createUser(userDto);

        return new ResponseEntity<>(userName, HttpStatus.CREATED);
    }

    @GetMapping("/{user-name}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user-name") String userName) {

        UserDto userDto = userService.findUserByName(userName);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/search/{user-id}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable("user-id") Integer userId) {

        UserDto userDto = userService.findUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

//    @GetMapping("/{user-id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable("user-id") Integer userId) {
//        UserDto userDto = userService.findUserById(userId);
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
//    @GetMapping("/search/{user-name}")
//    public ResponseEntity<UserDto> getUserByName(@PathVariable("user-name") String userName) {
//        UserDto userDto = userService.findUserByName(userName);
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
//
}
