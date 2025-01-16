package com.leun.user.service;

import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> users = userRepository.findAll().stream()
            .map(user -> UserDto.builder()
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .build())
            .collect(Collectors.toList());

        return users;
    }

    public UserDto getUserById(Integer userId) {
         User user = userRepository.findById(userId)
             .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

         UserDto userDto = UserDto.builder()
             .userName(user.getUserName())
             .userEmail(user.getUserEmail())
             .build();

         return userDto;
    }
}
