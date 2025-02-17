package com.leun.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.leun.auth.controller.JwtAuthenticationController;
import com.leun.auth.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = JwtAuthenticationController.class)
public class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private Authentication authentication;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void authenticate_shouldReturnToken() throws Exception {

        String expectedToken = "dummy-jwt-token";

        when(authentication.getName()).thenReturn("admin");

        when(jwtService.createToken(authentication)).thenReturn(expectedToken);


        when(jwtService.createToken(any())).thenReturn(expectedToken);

        mockMvc.perform(post("/v1/authentication")
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value(expectedToken));
    }
}
