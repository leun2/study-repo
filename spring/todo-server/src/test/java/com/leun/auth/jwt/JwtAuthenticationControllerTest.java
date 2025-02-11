package com.leun.auth.jwt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = JwtAuthenticationController.class)
public class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtTokenService jwtTokenService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void generateToken_shouldReturnJwtTokenResponse_whenDataIsValid() throws Exception {

        String username = "lee";
        String password = "1234";
        String mockToken = "mock.jwt.token";

        JwtTokenRequest request = new JwtTokenRequest(username, password);

        Authentication authenticationToken =
            new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication =
            authenticationManager.authenticate(authenticationToken);

        when(authenticationManager.authenticate(authenticationToken))
            .thenReturn(authentication);

        when(jwtTokenService.generateToken(authentication))
            .thenReturn(mockToken);

        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)) // 올바른 요청 본문
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(new JwtTokenResponse(mockToken))));
    }
}
