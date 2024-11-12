package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.UserDto;
import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/carRentals")
public class UserController {

    @Autowired
    AuthService userDetailsService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        return userDetailsService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity <Map<String,String>> login(@RequestBody UserDto request) {
        Map<String,String> response = new HashMap<>();
        boolean isVerified = userDetailsService.verifyUser(request.getUserName(), request.getPassword());
        if (isVerified) {
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }
    }
}
