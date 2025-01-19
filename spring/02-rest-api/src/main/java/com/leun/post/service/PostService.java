package com.leun.post.service;

import com.leun.exception.UserAlreadyExistsException;
import com.leun.post.dto.PostDto;
import com.leun.post.entity.Post;
import com.leun.post.repository.PostRepository;
import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDto> findAllPosts(Integer userId) {
        List<Post> posts = userRepository.findById(userId).get().getPosts();

        return posts.stream().map(
          post -> PostDto.builder()
              .postTitle(post.getPostTitle())
              .postDescription(post.getPostDescription())
              .build())
            .collect(Collectors.toList());
    }

    public Post createPost(Integer userId, PostDto postDto) {
        User user = userRepository.findById(userId).get();

        Post post = new Post(user, postDto.getPostTitle(), postDto.getPostDescription());

        return postRepository.save(post);
    }

}
