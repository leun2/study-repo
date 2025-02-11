package com.leun.rest.post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.post.controller.PostController;
import com.leun.post.dto.PostDto;
import com.leun.post.service.PostService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PostService postService;

    private PostDto postDto;

    private final Integer userId = 1;

    @BeforeEach
    void setUp() {
        postDto = PostDto.builder()
            .postTitle("mock data")
            .postDescription("mock data for unit test")
            .build();


    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee")
    void getPosts_shouldReturnPosts() throws Exception {

        List<PostDto> posts = new ArrayList<>();

        posts.add(postDto);

        posts.add(PostDto.builder()
            .postTitle("unit test")
            .postDescription("PostController Test")
            .build());

        when(postService.findPostsById(userId)).thenReturn(posts);

        mockMvc.perform(get("/users/{user-id}/post", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].postTitle")
                .value("mock data"))
            .andExpect(jsonPath("$[0].postDescription")
                .value("mock data for unit test"))
            .andExpect(jsonPath("$[1].postTitle")
                .value("unit test"))
            .andExpect(jsonPath("$[1].postDescription")
                .value("PostController Test"));

        verify(postService).findPostsById(userId);
    }

    @Test
    @WithMockUser(username = "lee")
    void createPost_shouldReturnUserWithLinks_whenValidDataProvided() throws Exception {

        String postJson = objectMapper.writeValueAsString(postDto);

        doNothing().when(postService).createPost(anyInt(), any(PostDto.class));

        mockMvc.perform(post("/users/{user-id}/post", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJson)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.postTitle").value("mock data"))
            .andExpect(jsonPath("$.postDescription").value("mock data for unit test"))
            .andExpect(jsonPath("$._links.posts.href").exists())
            .andExpect(jsonPath("$._links.posts.href").value("http://localhost/users/1/post"));

        verify(postService).createPost(anyInt(), any(PostDto.class));
    }
}
