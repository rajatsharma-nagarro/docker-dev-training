package com.docker.dev.training.service.impl;

import com.docker.dev.training.dto.request.UserRequestDto;
import com.docker.dev.training.model.User;
import com.docker.dev.training.repository.UserRepository;
import com.docker.dev.training.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public User getUserByUserName(String userName) {
        log.info("Fetching user with userName: {}", userName);
        Optional<User> user = userRepository.findByUsername(userName);
        return user.orElse(null);
    }

    @Override
    public User addUser(UserRequestDto userRequestDto) {
        log.info("Adding new userRequestDto: {}", userRequestDto.getUsername());
        Optional<User> existingUser = userRepository.findByUsername(userRequestDto.getUsername());
        if (existingUser.isPresent()) {
            return null;
        }
        User user = objectMapper.convertValue(userRequestDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public boolean loginUser(String username, String password) {
        log.info("Attempting login for user: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            log.info("Login successful for user: {}", username);
            return true;
        }
        log.info("Login failed for user: {}", username);
        return false;
    }
}
