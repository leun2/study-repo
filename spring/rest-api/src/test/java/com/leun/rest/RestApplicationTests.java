package com.leun.rest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.post.dto.PostDto;
import com.leun.post.entity.Post;
import com.leun.post.repository.PostRepository;
import com.leun.post.service.PostService;
import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import com.leun.user.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;

	private Integer userId;

	private User mockUser;
	private Post mockPost;

	@BeforeEach
	void setUp() {
		User mockUser = userRepository.save(new User("lee", "lee@example.com"));
		Post mockPost = postRepository.save(new Post(mockUser, "test title", "test description"));

		userId = mockUser.getUserId();  // 실제 저장된 ID 할당
	}

	@AfterEach
	@Transactional
	void tearDown() {
		postRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	@WithMockUser(username = "lee")
	public void testCreateUser() throws Exception {
		UserDto user = UserDto.builder()
			.userName("user")
			.userEmail("user@example.com")
			.build();

		mockMvc.perform(post("/users/authorize")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userName").value("user"))
			.andExpect(jsonPath("$.userEmail").value("user@example.com"))
			.andExpect(jsonPath("$._links.self.href").exists()) // self 링크 존재 확인
			.andExpect(jsonPath("$._links.users.href").exists());
	}

	@Test
	@WithMockUser(username = "lee")
	public void testCreatePost() throws Exception {

		PostDto postDto = PostDto.builder()
			.postTitle("mock title")
			.postDescription("mock description")
			.build();

		mockMvc.perform(post("/users/{user-id}/post", userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(postDto))
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.postTitle").value("mock title"))
			.andExpect(jsonPath("$.postDescription").value("mock description"))
			.andExpect(jsonPath("$._links.posts.href").exists())
			.andExpect(jsonPath("$._links.posts.href")
				.value(String.format("http://localhost/users/%d/post", userId)));
	}

	@Test
	@WithMockUser(username = "lee")
	public void testGetUser() throws Exception {
		mockMvc.perform(get("/users/{user-id}", userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userName").value("lee"))
			.andExpect(jsonPath("$.userEmail").value("lee@example.com"));
	}

	@Test
	@WithMockUser(username = "lee")
	public void testGetPost() throws Exception {
		mockMvc.perform(get("/users/{user-id}/post", userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].postTitle").value("test title"))
			.andExpect(jsonPath("$[0].postDescription").value("test description"));
	}

	@Test
	@WithMockUser(username = "lee")
	public void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/users/{user-id}", userId)
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()))
			.andExpect(status().isSeeOther());

		// 사용자가 삭제된 후 다시 조회 시 404 상태 반환
		mockMvc.perform(get("/users/{user-id}", userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}
