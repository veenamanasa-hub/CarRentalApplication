package com.car.rentals.CarRentals.dto;

import lombok.Data;
import java.util.Date;

@Data
public class BookingDto {
    private Long bookingId;
    private Date fromDate;
    private Date toDate;
    private Long price;
    private Long userId;
    private Long carId;
    private String userName;
    private String carName;
}
