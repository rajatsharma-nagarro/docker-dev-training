package com.docker.dev.training.service;

import com.docker.dev.training.dto.request.UserRequestDto;
import com.docker.dev.training.model.User;

/**
 * Service for user operations.
 */
public interface UserService {
    User getUserByUserName(String id);

    User addUser(UserRequestDto userRequestDto);

    boolean loginUser(String username, String password);
}
