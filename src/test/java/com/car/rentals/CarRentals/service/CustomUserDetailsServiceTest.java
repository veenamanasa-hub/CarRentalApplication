package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testLoadUserByUsername_Success() {

        User user = new User();
        user.setUserName("testUser");
        user.setPassword("hashedPassword123");
        user.setRole("USER");

        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("hashedPassword123", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {

        when(userRepository.findByUserName("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("nonExistentUser");
        });
    }
}
