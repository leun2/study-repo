package com.leun.auth.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
public class JwtTokenServiceTest {

    @InjectMocks
    private JwtTokenService tokenService;

    @Mock
    JwtEncoder jwtEncoder;

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void generateToken_shouldReturnToken() {

        String username = "lee";
        String password = "1234";

        Authentication authentication =
            new UsernamePasswordAuthenticationToken(username, password);

        String mockScope = authentication
            .getAuthorities()
            .stream().map(
                GrantedAuthority::getAuthority
            ).collect(Collectors.joining(" "));

        JwtClaimsSet mockClaims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claim("scope", mockScope)
            .build();

        String mockTokenValue = "mock.jwt.token";
        JwtEncoderParameters mockParams = JwtEncoderParameters.from(mockClaims);

        Jwt jwt = Jwt.withTokenValue(mockTokenValue)
            .header("alg", "HS256")
            .claims(claims -> claims.putAll(mockClaims.getClaims()))
            .build();

        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);

        // When
        String result = tokenService.generateToken(authentication);

        // Then
        assertNotNull(result);
        assertEquals(mockTokenValue, result);
    }
}
