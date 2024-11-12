package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.CarDto;
import com.car.rentals.CarRentals.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(carService);
    }

    @Test
    @WithMockUser(username = "user") // Mock user for authentication
    public void testGetAllAvailableCars() throws Exception {
        // Arrange
        CarDto car1 = new CarDto();
        car1.setCarId(1L);
        car1.setCarName("Sedan");
        car1.setCount(5);

        CarDto car2 = new CarDto();
        car2.setCarId(2L);
        car2.setCarName("SUV");
        car2.setCount(3);

        List<CarDto> carList = Arrays.asList(car1, car2);
        when(carService.getAvailableCars()).thenReturn(carList);

        // Act & Assert
        mockMvc.perform(get("/carRentals/getCars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Sedan','count':5}, {'id':2,'name':'SUV','count':3}]"));
    }
}
