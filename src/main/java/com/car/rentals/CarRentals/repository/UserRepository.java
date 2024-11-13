package com.car.rentals.CarRentals.repository;

import com.car.rentals.CarRentals.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
