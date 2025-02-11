package com.leun.user;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leun.user.controller.UserController;
import com.leun.user.dto.UserDto;
import com.leun.user.service.UserService;
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
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private UserDto mockUser;

    @BeforeEach
    void setUp() {

        mockUser = UserDto.builder()
            .userName("lee")
            .userPassword("1234")
            .build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getUsers_shouldReturnUserList() throws Exception {

        when(userService.findAllUsers()).thenReturn(List.of(mockUser));

        mockMvc.perform(get("/v1/user"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].userName").value("lee"))
            .andExpect(jsonPath("$[0].userPassword").value("1234"));

        verify(userService).findAllUsers();
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void createUser_shouldReturnUser_whenDataIsValid() throws Exception {

        when(userService.createUser(mockUser)).thenReturn(mockUser.getUserName());

        String result = objectMapper.writeValueAsString(mockUser);

        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(content().string(mockUser.getUserName()));

        verify(userService).createUser(eq(mockUser));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getUserByName_shouldReturnUser_WhenUserNameIsValid() throws Exception {

        when(userService.findUserByName(mockUser.getUserName())).thenReturn(mockUser);

        mockMvc.perform(get("/v1/user/{user-name}", mockUser.getUserName()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value(mockUser.getUserName()))
            .andExpect(jsonPath("$.userPassword").value(mockUser.getUserPassword()));

        verify(userService).findUserByName(eq(mockUser.getUserName()));
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void getUserById_shouldReturnUser_WhenUserIdIsValid() throws Exception {

        when(userService.findUserById(anyInt())).thenReturn(mockUser);

        mockMvc.perform(get("/v1/user/search/{user-id}", 1))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value(mockUser.getUserName()))
            .andExpect(jsonPath("$.userPassword").value(mockUser.getUserPassword()));

        verify(userService).findUserById(eq(1));
    }
}
