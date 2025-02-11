package com.leun.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import com.leun.user.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
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


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findAllUsers_shouldReturnUserList() {

        User mockUser = new User("lee", "1234");

        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        List<UserDto> result = userService.findAllUsers();

        // Assertions: 반환된 리스트의 첫 번째 UserDto 값이 mockUser에서 변환된 값이어야 함
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("lee", result.get(0).getUserName());
        assertEquals("1234", result.get(0).getUserPassword());

        // Verifying that findAll() method in userRepository was called exactly once
        verify(userRepository).findAll();
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void createUser_shouldReturnUserName_whenUserDoesNotExist() {

        // Given: UserDto 생성 (변수명 일관성)
        UserDto mockDto = UserDto.builder()
            .userName("lee")
            .userPassword("1234")
            .build();

        // User 객체 준비
        User mockUser = new User("lee", "1234");

        // When: userRepository.save()가 mockUser를 반환하도록 설정
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // When: userService.createUser 호출
        String mockName = userService.createUser(mockDto);

        // Then: userRepository.save() 호출 검증
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        // Captured User 검증
        User savedUser = userCaptor.getValue();
        assertEquals(mockDto.getUserName(), savedUser.getUserName());
        assertEquals(mockDto.getUserPassword(), savedUser.getUserPassword());

        // 반환 값 검증
        assertEquals("lee", mockName);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findUserById_shouldReturnUser_whenUserExist() {

        Integer mockId = 1;

        // User 객체 준비
        User mockUser = new User("lee", "1234");

        when(userRepository.findById(mockId)).thenReturn(Optional.of(mockUser));

        UserDto result = userService.findUserById(mockId);

        assertNotNull(result);
        assertThat(result.getUserName()).isEqualTo(mockUser.getUserName());
        assertThat(result.getUserPassword()).isEqualTo(mockUser.getUserPassword());

        verify(userRepository).findById(mockId);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findUserByName_shouldReturnUser_whenUserExist() {

        String mockName = "lee";

        // User 객체 준비
        User mockUser = new User("lee", "1234");

        when(userRepository.findByUserName(mockName)).thenReturn(Optional.of(mockUser));

        UserDto result = userService.findUserByName(mockName);

        assertNotNull(result);
        assertThat(result.getUserName()).isEqualTo(mockUser.getUserName());
        assertThat(result.getUserPassword()).isEqualTo(mockUser.getUserPassword());

        verify(userRepository).findByUserName(mockName);
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findUserById_shouldThrowException_whenUserDoesNotExist() {
        Integer mockId = 1;
        when(userRepository.findById(mockId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.findUserById(mockId);
        });
    }

    @Test
    @WithMockUser(username = "lee", roles = {"USER"}, authorities = {"read"})
    void findUserByName_shouldThrowException_whenUserDoesNotExist() {
        String mockName = "lee";
        when(userRepository.findByUserName(mockName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.findUserByName(mockName);
        });
    }
}
