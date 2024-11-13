package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.CarDto;
import com.car.rentals.CarRentals.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carRentals")
public class CarController {

    @Autowired
    CarService carService;

    /**
     * API endpoint to get the list of all available cars
     * @return List<CarDto></CarDto>
     */
    @GetMapping("/getCars")
    public ResponseEntity<List<CarDto>> getAllAvailableCars() {
        List<CarDto> carList = carService.getAvailableCars();
        return ResponseEntity.ok(carList);
    }

}
