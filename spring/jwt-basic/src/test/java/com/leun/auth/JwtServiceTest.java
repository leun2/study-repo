package com.leun.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.auth.service.JwtService;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void createToken_shouldReturnToken() {

        // Arrange
        String username = "admin";
        String expectedToken = "encodedToken";
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(120))
            .subject(username)
            .claim("scope", "ROLE_ADMIN ROLE_USER")
            .build();

        Jwt jwt = mock(Jwt.class);
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn(expectedToken);

        Collection<SimpleGrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
        );

        when(authentication.getName()).thenReturn(username);
        when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        // Act
        String token = jwtService.createToken(authentication);

        // Assert
        assertEquals(expectedToken, token);
        verify(jwtEncoder).encode(any(JwtEncoderParameters.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void createScope_shouldReturnAuthorities() {

        // Arrange
        String expectedScope = "ROLE_ADMIN ROLE_USER";

        Collection<SimpleGrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
        );

        when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        // Act
        String scope = jwtService.createScope(authentication);

        // Assert
        assertEquals(expectedScope, scope);
    }
}
