package com.leun.rest.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.user.controller.UserController;
import com.leun.user.dto.UserDto;
import com.leun.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
            .userName("lee")
            .userEmail("lee@example.com")
            .build();
    }

    @AfterEach
    void tearDown() {
        userDto = null;
    }

    @Test
    @WithMockUser(username = "lee")
    void getUsers_shouldReturnUsers() throws Exception {

        List<UserDto> users = new ArrayList<>();

        users.add(userDto);

        users.add(UserDto.builder()
                .userEmail("kim@example.com")
                .userName("kim")
            .build());

        when(userService.findUsersById()).thenReturn(users);

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].userName").value("lee"))
            .andExpect(jsonPath("$[0].userEmail").value("lee@example.com"))
            .andExpect(jsonPath("$[1].userName").value("kim"))
            .andExpect(jsonPath("$[1].userEmail").value("kim@example.com"));

        verify(userService).findUsersById();
    }

    @Test
    @WithMockUser(username = "lee")
    void getUser_shouldReturnUser_whenUserExists() throws Exception {

        when(userService.findUserById(anyInt())).thenReturn(userDto);

        // when & then
        mockMvc.perform(get("/users/{user-id}", 1)) // userId = 1 설정
            .andExpect(status().isOk()) // HTTP 200 확인
            .andExpect(jsonPath("$.userName").value(userDto.getUserName())) // JSON 응답 값 검증
            .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail())); // 추가 검증 가능

        verify(userService).findUserById(anyInt());
    }

    @Test
    @WithMockUser(username = "lee")
    void createUser_shouldReturnUserWithLinks_whenValidDataProvided() throws Exception {

        // given
        String userJson = objectMapper.writeValueAsString(userDto);

        doNothing().when(userService).createUser(any(UserDto.class));

        // when & then
        mockMvc.perform(post("/users/authorize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value("lee")) // 사용자 이름 확인
            .andExpect(jsonPath("$.userEmail").value("lee@example.com")) // 이메일 확인
            .andExpect(jsonPath("$._links.self.href").exists()) // self 링크 존재 확인
            .andExpect(jsonPath("$._links.users.href").exists()); // users 링크 존재 확인

        verify(userService).createUser(any(UserDto.class));
    }

    @Test
    @WithMockUser(username = "lee")
    void deleteUser_shouldRedirectToUser_whenUserDeleted() throws Exception {
        Integer userId = 1;
        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{user-id}", userId)
                .with(csrf())) // DELETE 요청
            .andExpect(status().isSeeOther()) // HTTP 303 응답 확인
            .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/users"));

        verify(userService).deleteUser(userId);
    }
}
