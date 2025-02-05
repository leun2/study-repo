package com.leun.user.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.leun.user.dto.UserDto;
import com.leun.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        List<UserDto> users = userService.findAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user-id") Integer userId) {
        UserDto user = userService.findUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    public ResponseEntity<String> postUser(@RequestBody @Valid UserDto userDto) {
//        User user = userService.createUser(userDto);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{id}")
//            .buildAndExpand(user.getUserId())
//            .toUri();
//
//        return ResponseEntity.created(location)
//            .body("User created with Name: " + user.getUserName());
//
//        String location = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{id}")
//            .buildAndExpand(user.getUserId())
//            .toUriString();
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//            .header("Location", location)
//            .body("User created with Name: " + user.getUserName());
//    }

    @PostMapping
    public EntityModel<UserDto> postUser(@RequestBody @Valid UserDto userDto) {
        userService.createUser(userDto);

        // 현재 사용자에 대한 링크 추가
        Link selfLink = linkTo(methodOn(UserController.class).postUser(userDto)).withSelfRel();

        // 모든 사용자 목록으로 가는 링크 추가
        Link allUsersLink = linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users");

        // EntityModel로 사용자 정보와 링크를 반환
        return EntityModel.of(userDto, selfLink, allUsersLink);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user-id") Integer userId) {
        userService.deleteUser(userId);

        String redirectUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/delete-success")
            .toUriString();

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header("Location", redirectUrl)
            .build();
    }
}
