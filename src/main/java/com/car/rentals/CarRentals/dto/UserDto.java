package com.car.rentals.CarRentals.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private String role;
}
