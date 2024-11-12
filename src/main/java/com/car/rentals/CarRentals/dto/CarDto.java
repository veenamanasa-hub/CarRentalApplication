package com.car.rentals.CarRentals.dto;

import lombok.Data;

@Data
public class CarDto {
    private long carId;
    private String carName;
    private int count;
    private String description;
    private long price;
}
