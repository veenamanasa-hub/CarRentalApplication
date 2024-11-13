package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.BookingDto;
import com.car.rentals.CarRentals.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carRentals")
public class BookingController {

    @Autowired
    BookingService bookingService;

    /**
     * Book car based on details provided in BookingDto
     * @param bookingDto
     * @return Map<String,String>
     */
    @PostMapping("/bookCar")
    public ResponseEntity<Map<String,String>> bookCar(@RequestBody BookingDto bookingDto) {
        boolean success = bookingService.bookCar(bookingDto);
        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "Booking successful");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Failed to book the car");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
