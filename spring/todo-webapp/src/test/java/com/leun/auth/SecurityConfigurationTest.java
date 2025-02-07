package com.leun.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.leun.auth.config.SecurityConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@ExtendWith(MockitoExtension.class)
class SecurityConfigurationTest {

    @InjectMocks
    private SecurityConfiguration securityConfiguration;

    @Mock
    private PasswordEncoder passwordEncoder;

    private InMemoryUserDetailsManager userDetailsManager;

    @BeforeEach
    void setUp() {
        userDetailsManager = securityConfiguration.createUserDetailsManager();
    }

    @Test
    void testCreateUserDetailsManager() {
        UserDetails userLee = userDetailsManager.loadUserByUsername("lee");
        UserDetails userKim = userDetailsManager.loadUserByUsername("kim");

        assertNotNull(userLee);
        assertNotNull(userKim);
        assertEquals("lee", userLee.getUsername());
        assertEquals("kim", userKim.getUsername());
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder encoder = securityConfiguration.passwordEncoder();
        String rawPassword = "1234";
        String encodedPassword = encoder.encode(rawPassword);

        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}