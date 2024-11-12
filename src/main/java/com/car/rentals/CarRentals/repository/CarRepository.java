package com.car.rentals.CarRentals.repository;

import com.car.rentals.CarRentals.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByCountGreaterThan(int count);
    Optional<Car> findByCarName(String carName);

}
