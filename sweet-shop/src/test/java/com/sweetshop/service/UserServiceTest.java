package com.sweetshop.service;

import com.sweetshop.model.User;
import com.sweetshop.repository.UserRepository;
import com.sweetshop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        when(userRepository.existsByUsername("alok")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");

        User user = userService.registerUser("alok", "pass");

        assertEquals("alok", user.getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldFailIfUsernameAlreadyExists() {
        when(userRepository.existsByUsername("alok")).thenReturn(true);

        assertThrows(
                RuntimeException.class,
                () -> userService.registerUser("alok", "pass")
        );
    }
}
