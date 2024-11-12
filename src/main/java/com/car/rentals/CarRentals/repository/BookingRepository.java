package com.car.rentals.CarRentals.repository;

import com.car.rentals.CarRentals.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
