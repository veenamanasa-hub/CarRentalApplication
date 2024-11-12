package com.car.rentals.CarRentals.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.car.rentals.CarRentals.dto.BookingDto;
import com.car.rentals.CarRentals.entity.Booking;
import com.car.rentals.CarRentals.entity.Car;
import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.BookingRepository;
import com.car.rentals.CarRentals.repository.CarRepository;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookCar_Success() {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(1L);
        bookingDto.setUserId(1L);
        bookingDto.setFromDate(new Date());
        bookingDto.setToDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(5)));

        Car car = new Car();
        car.setCarId(1L);
        car.setPrice(100);
        car.setCount(5);

        User user = new User();
        user.setUserId(1L);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookingRepository.save(any(Booking.class))).thenReturn(new Booking());

        // Act
        boolean result = bookingService.bookCar(bookingDto);

        // Assert
        assertTrue(result);
        assertEquals(4, car.getCount()); // Car count should be reduced by 1
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testBookCar_CarNotFound() {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(1L);
        bookingDto.setUserId(1L);
        bookingDto.setFromDate(new Date());
        bookingDto.setToDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(5)));

        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        // Act
        boolean result = bookingService.bookCar(bookingDto);

        // Assert
        assertFalse(result);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    public void testBookCar_UserNotFound() {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(1L);
        bookingDto.setUserId(1L);
        bookingDto.setFromDate(new Date());
        bookingDto.setToDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(5)));

        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        boolean result = bookingService.bookCar(bookingDto);

        // Assert
        assertFalse(result);
        verify(bookingRepository, never()).save(any(Booking.class));
    }
}
