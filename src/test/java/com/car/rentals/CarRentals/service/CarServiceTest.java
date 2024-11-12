package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.dto.CarDto;
import com.car.rentals.CarRentals.entity.Car;
import com.car.rentals.CarRentals.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAvailableCars() {

        Car car1 = new Car();
        car1.setCarId(1L);
        car1.setCarName("Sedan");
        car1.setCount(3);

        Car car2 = new Car();
        car2.setCarId(2L);
        car2.setCarName("Van");
        car2.setCount(5);

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<CarDto> availableCars = carService.getAvailableCars();

        // Assert
        assertEquals(2, availableCars.size());
        assertEquals("Sedan", availableCars.get(0).getCarName());
        assertEquals("Van", availableCars.get(1).getCarName());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testGetAvailableCars_NoCarsAvailable() {

        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        List<CarDto> availableCars = carService.getAvailableCars();

        // Assert
        assertTrue(availableCars.isEmpty(), "The availableCars list should be empty");
        verify(carRepository, times(1)).findAll();
    }
}

