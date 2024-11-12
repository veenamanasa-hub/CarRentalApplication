package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.dto.CarDto;
import com.car.rentals.CarRentals.entity.Car;
import com.car.rentals.CarRentals.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService{

@Autowired
CarRepository carRepository;

public List<CarDto>getAvailableCars(){
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
}
}
