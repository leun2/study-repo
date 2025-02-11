package com.leun.rest.post;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.post.dto.PostDto;
import com.leun.post.entity.Post;
import com.leun.post.repository.PostRepository;
import com.leun.post.service.PostService;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    private final Integer userId = 1;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee")
    void findPostsById_shouldReturnPostList_whenUserExist() {

        User mockUser = new User("lee","lee@example.com");

        List<Post> posts =
            List.of(
                new Post(mockUser, "mock", "test description"),
                new Post(mockUser, "title", "for PostService"));

        mockUser.setPosts(posts);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        List<PostDto> result = postService.findPostsById(userId);

        assertEquals(2, result.size());
        assertEquals("mock", result.get(0).getPostTitle());
        assertEquals("test description", result.get(0).getPostDescription());
        assertEquals("title", result.get(1).getPostTitle());
        assertEquals("for PostService", result.get(1).getPostDescription());

        // userRepository.findById()가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @WithMockUser(username = "lee")
    void findPostsById_shouldThrowException_whenUserDoesNotExist() {
        // Given: 사용자가 존재하지 않는 상황
        Integer userId = 1;

        // userRepository.findById()가 빈 Optional을 반환하도록 시뮬레이션
        doThrow(new NoSuchElementException("User not found"))
            .when(userRepository).findById(userId);

        // When & Then: 예외가 발생하는지 검증
        assertThrows(NoSuchElementException.class, () -> postService.findPostsById(userId));

        // userRepository.findById()가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @WithMockUser(username = "lee")
    void createPost_shouldCreatePost_whenUserExist() {

        User mockUser = new User("lee","lee@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        PostDto mockPost = PostDto.builder()
            .postTitle("mock title")
            .postDescription("mock description")
            .build();

        assertDoesNotThrow(() -> postService
                .createPost(userId, mockPost));

        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(postCaptor.capture());

        Post savePost = postCaptor.getValue();
        assertEquals(mockPost.getPostTitle(), savePost.getPostTitle());
        assertEquals(mockPost.getPostDescription(), savePost.getPostDescription());
    }
}
