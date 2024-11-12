package com.car.rentals.CarRentals.entity;

import com.car.rentals.CarRentals.dto.CarDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long carId;
    private String carName;
    private int count;
    private String description;

    private long price;

    public CarDto getCarDto(){
        CarDto carDto = new CarDto();
        carDto.setCarId(carId);
        carDto.setCarName(carName);
        carDto.setCount(count);
        carDto.setDescription(description);
        carDto.setPrice(price);
        return carDto;
    }


}
