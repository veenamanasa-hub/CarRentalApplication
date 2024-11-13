package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.builder;

@Service
    public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return builder()
                    .username(user.getUserName())
                    .password(user.getPassword()) // Password should be hashed
                    .roles(user.getRole())
                    .build();
        }
}
