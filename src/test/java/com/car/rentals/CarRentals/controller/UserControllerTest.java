package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.UserDto;
import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "user") // Simulate an authenticated user
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("password");

        Mockito.when(authService.registerUser(any(User.class))).thenReturn("User registered successfully");

        mockMvc.perform(post("/carRentals/register")
                        .with(csrf()) // Add CSRF token
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    @WithMockUser(username = "user") // Simulate an authenticated user
    void testLoginSuccessful() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setPassword("password");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");

        Mockito.when(authService.verifyUser(eq("testUser"), eq("password"))).thenReturn(true);

        mockMvc.perform(post("/carRentals/login")
                        .with(csrf()) // Add CSRF token
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    @WithMockUser(username = "user") // Simulate an authenticated user
    void testLoginFailure() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setPassword("wrongPassword");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid username or password");

        Mockito.when(authService.verifyUser(eq("testUser"), eq("wrongPassword"))).thenReturn(false);

        mockMvc.perform(post("/carRentals/login")
                        .with(csrf()) // Add CSRF token
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
  }
