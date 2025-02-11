package com.leun.rest.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import com.leun.user.service.UserService;
import java.util.List;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    private final Integer userId = 1;

    private final UserDto mockUserDto = UserDto.builder()
        .userName("TestUser")
        .userEmail("test@example.com")
        .build();

    @BeforeEach
    void setUp() {
        mockUser = new User("lee", "lee@example.com");
    }

    @AfterEach
    void tearDown() {
        mockUser = null;
    }

    @Test
    @WithMockUser(username = "lee")
    void findUsersById_shouldReturnUserList() {

        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        List<UserDto> result = userService.findUsersById();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserName()).isEqualTo(mockUser.getUserName());
        assertThat(result.get(0).getUserEmail()).isEqualTo(mockUser.getUserEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "lee")
    void findUserById_shouldReturnUser_whenUserExists() {

        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(mockUser));

        UserDto result = userService.findUserById(userId);

        assertNotNull(result);
        assertThat(result.getUserName()).isEqualTo(mockUser.getUserName());
        assertThat(result.getUserEmail()).isEqualTo(mockUser.getUserEmail());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @WithMockUser(username = "lee")
    void createUser_shouldCreateUser_whenUserDoesNotExist() throws Exception {

        // Given: 사용자가 존재하지 않는 상황을 시뮬레이션
        when(userRepository.findByUserEmail(mockUserDto.getUserEmail()))
            .thenReturn(Optional.empty());

        User user = new User(mockUserDto.getUserName(),mockUserDto.getUserEmail());

        // When & Then: 예외 없이 createUser가 정상적으로 실행되는지 검증
        assertDoesNotThrow(() -> userService.createUser(mockUserDto));

        // ArgumentCaptor를 사용해 save() 메서드 호출된 객체 캡처
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(mockUserDto.getUserName(), savedUser.getUserName());
        assertEquals(mockUserDto.getUserEmail(), savedUser.getUserEmail());
    }

    @Test
    @WithMockUser(username = "lee")
    void deleteUser_shouldDeleteUser_whenUserExist() {

        // Given: 사용자 존재 시나리오
        when(userRepository.existsById(userId)).thenReturn(true);

        // When: deleteUser가 호출되었을 때
        userService.deleteUser(userId);

        // Then: userRepository.deleteById가 한 번 호출되었는지 검증
        verify(userRepository, times(1)).deleteById(userId);
    }
}
