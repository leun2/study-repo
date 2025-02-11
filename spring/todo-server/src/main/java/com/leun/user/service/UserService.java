package com.leun.user.service;

import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAllUsers() {
        List<User>users = userRepository.findAll();

        return users.stream()
            .map(user -> UserDto.builder()
                .userName(user.getUserName())
                .userPassword(user.getUserPassword())
                .build())
            .collect(Collectors.toList());
    }

    public String createUser(UserDto userDto) {
        User user = new User(userDto.getUserName(), userDto.getUserPassword());
        User saveUser = userRepository.save(user);

        return saveUser.getUserName();
    }

    public UserDto findUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new NoSuchElementException("User Does Not Exist")
        );

        return UserDto.builder()
            .userName(user.getUserName())
            .userPassword(user.getUserPassword())
            .build();
    }

    public UserDto findUserByName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(
            () -> new NoSuchElementException("User Does Not Exist")
        );

        return UserDto.builder()
            .userName(user.getUserName())
            .userPassword(user.getUserPassword())
            .build();
    }
}
