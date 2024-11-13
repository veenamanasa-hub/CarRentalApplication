package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // For hashing passwords

    /**
     * This method is used to register new user
     * @param user
     * @return String
     */
    public String registerUser(User user) {
        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            return "Username already taken.";
        }

        // Hash the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully.";
    }

    public boolean verifyUser(String userName, String password) {
        User user = userRepository.findByUserName(userName).get();
        if (user != null && BCrypt.checkpw(password,user.getPassword())) {
            return true;
        }
        return false;
    }
}

