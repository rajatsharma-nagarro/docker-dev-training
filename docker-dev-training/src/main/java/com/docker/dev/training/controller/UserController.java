package com.docker.dev.training.controller;

import com.docker.dev.training.dto.request.UserRequestDto;
import com.docker.dev.training.model.User;
import com.docker.dev.training.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for user operations.
 */
@RestController
@Slf4j
@RequestMapping("/user/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Fetch a user by ID.
     *
     * @param userName User Name
     * @return User object
     */
    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        log.info("Fetching user with ID: {}", userName);
        return userService.getUserByUserName(userName);
    }

    /**
     * Add a new user.
     *
     * @param userRequestDto User object from request body
     * @return Created User object
     */
    @PostMapping
    public User addUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Adding new user: {}", userRequestDto.getUsername());
        return userService.addUser(userRequestDto);
    }

    /**
     * Login a user.
     *
     * @param userRequestDto User object with username and password
     * @return Login status message
     */
    @PostMapping("/login")
    public boolean loginUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Attempting login for user: {}", userRequestDto.getUsername());
        boolean success = userService.loginUser(userRequestDto.getUsername(), userRequestDto.getPassword());
        log.info("Login {} for user: {}", success ? "successful" : "failed", userRequestDto.getUsername());
        return success;
    }
}
