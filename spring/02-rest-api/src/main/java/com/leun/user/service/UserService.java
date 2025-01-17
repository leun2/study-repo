package com.leun.user.service;

import com.leun.exception.UserAlreadyExistsException;
import com.leun.exception.UserNotFoundException;
import com.leun.user.dto.UserDto;
import com.leun.user.entity.User;
import com.leun.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAllUsers() {
        List<UserDto> users = userRepository.findAll().stream()
            .map(user -> UserDto.builder()
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .build())
            .collect(Collectors.toList());

        return users;
    }

    public UserDto findUserById(Integer userId) {
         User user = userRepository.findById(userId)
             .orElseThrow(() -> {
                 log.warn("User not found with ID: {}", userId);
                 return new UserNotFoundException("User with ID " + userId + " does not exist");
             });

         UserDto userDto = UserDto.builder()
             .userName(user.getUserName())
             .userEmail(user.getUserEmail())
             .build();

         return userDto;
    }

    public User createUser(UserDto userDto) {
        userRepository.findByUserEmail(userDto.getUserEmail())
            .ifPresent(existUser -> {
                log.warn("User already exists with EMAIL: {}", userDto.getUserEmail());
                throw new UserAlreadyExistsException("User with email " + userDto.getUserEmail() + " already exists.");
            });


        User user = new User(userDto.getUserName(), userDto.getUserEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }
}
