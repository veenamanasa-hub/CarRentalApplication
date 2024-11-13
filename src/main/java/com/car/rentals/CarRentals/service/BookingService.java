package com.car.rentals.CarRentals.service;

import com.car.rentals.CarRentals.dto.BookingDto;
import com.car.rentals.CarRentals.entity.Booking;
import com.car.rentals.CarRentals.entity.Car;
import com.car.rentals.CarRentals.entity.User;
import com.car.rentals.CarRentals.repository.BookingRepository;
import com.car.rentals.CarRentals.repository.CarRepository;
import com.car.rentals.CarRentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    public boolean bookCar(BookingDto bookingDTO){
        Optional<Car> optionalCar = carRepository.findByCarName(bookingDTO.getCarName());
        Optional<User> optionalUser = userRepository.findByUserName(bookingDTO.getUserName());

        if(optionalCar.isPresent() && optionalUser.isPresent() && optionalCar.get().getCount()>0){
            Car existingCar = optionalCar.get();
            Booking booking = new Booking();
            booking.setUser(optionalUser.get());
            booking.setCar(optionalCar.get());

            long diffInMilliSec = bookingDTO.getToDate().getTime() - bookingDTO.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSec);

            booking.setPrice(existingCar.getPrice()*days);
            bookingRepository.save(booking);
            existingCar.setCount(existingCar.getCount()-1);
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }

}
