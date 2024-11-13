package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("password123");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty()); // No existing user
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword123");

        // Act
        String result = authService.registerUser(user);

        // Assert
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUser_UsernameAlreadyTaken() {
        // Arrange
        User existingUser = new User();
        existingUser.setUserName("testUser");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(existingUser)); // Existing user

        User newUser = new User();
        newUser.setUserName("testUser");
        newUser.setPassword("password123");

        // Act
        String result = authService.registerUser(newUser);

        // Assert
        assertEquals("Username already taken.", result);
    }

    }
