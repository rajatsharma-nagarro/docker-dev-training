package com.docker.dev.training.service;

import com.docker.dev.training.dto.request.UserRequestDto;
import com.docker.dev.training.model.User;
import com.docker.dev.training.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserServiceImplTest {

    @InjectMocks
    private com.docker.dev.training.service.impl.UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getUserByName_UserExists_ReturnsUser() {
        User user = new User();
        user.setId("1");
        when(userRepository.findByUsername("1")).thenReturn(Optional.of(user));

        User result = userService.getUserByUserName("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void getUserByUserName_UserNotFound_ReturnsNull() {
        when(userRepository.findByUsername("2")).thenReturn(Optional.empty());

        User result = userService.getUserByUserName("2");

        assertNull(result);
    }

    @Test
    void addUser_ValidRequest_SavesAndReturnsUser() {
        UserRequestDto dto = new UserRequestDto();
        dto.setUsername("testuser");
        dto.setPassword("pass");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("pass");

        when(objectMapper.convertValue(dto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(dto);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void loginUser_ValidCredentials_ReturnsTrue() {
        String username = "user";
        String password = "pass";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        boolean result = userService.loginUser(username, password);

        assertTrue(result);
    }

    @Test
    void loginUser_InvalidCredentials_ReturnsFalse() {
        String username = "user";
        String password = "wrong";
        User user = new User();
        user.setUsername(username);
        user.setPassword("pass");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        boolean result = userService.loginUser(username, password);

        assertFalse(result);
    }

    @Test
    void loginUser_UserNotFound_ReturnsFalse() {
        when(userRepository.findByUsername("nouser")).thenReturn(Optional.empty());

        boolean result = userService.loginUser("nouser", "pass");

        assertFalse(result);
    }
}
