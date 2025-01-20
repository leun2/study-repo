package com.leun.post.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.leun.post.dto.PostDto;
import com.leun.post.repository.PostRepository;
import com.leun.post.service.PostService;
import com.leun.user.controller.UserController;
import com.leun.user.dto.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{user-id}")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable("user-id") Integer userId) {
        List<PostDto> posts = postService.findAllPosts(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/post")
    public EntityModel<PostDto> postPost(@PathVariable("user-id") Integer userId, @RequestBody @Valid PostDto postDto) {
        postService.createPost(userId, postDto);

        Link allPostsLink = linkTo(methodOn(PostController.class).getAllPosts(userId)).withRel("all-posts");

        return EntityModel.of(postDto, allPostsLink);
    }
}
