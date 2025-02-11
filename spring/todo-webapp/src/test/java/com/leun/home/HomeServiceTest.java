package com.leun.home;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.home.service.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
class HomeServiceTest {

    @InjectMocks
    private HomeService homeService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void getUserName_shouldReturnAuthenticatedUserName() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("lee");

        // When: getUserName() 호출
        String userName = homeService.getUserName();

        // Then: "lee"가 반환되는지 검증
        assertThat(userName).isEqualTo("lee");

        // Verify: getAuthentication()과 getName()이 한 번 호출되었는지 확인
        verify(securityContext).getAuthentication();
        verify(authentication).getName();
    }
}
