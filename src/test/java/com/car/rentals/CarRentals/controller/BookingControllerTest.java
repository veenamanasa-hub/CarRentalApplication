package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.BookingDto;
import com.car.rentals.CarRentals.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    private Date fromDate;
    private Date toDate;

    @BeforeEach
    public void setUp() {
        Mockito.reset(bookingService);
        LocalDate localDate = LocalDate.now();
        fromDate = java.sql.Date.valueOf(localDate);
        toDate = java.sql.Date.valueOf(localDate.plusDays(3));
    }

    @Test
    public void testBookCar_Success() throws Exception {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(1L);
        bookingDto.setUserId(1L);
        bookingDto.setFromDate(fromDate);
        bookingDto.setToDate(toDate);

        when(bookingService.bookCar(bookingDto)).thenReturn(true);

        mockMvc.perform(post("/carRentals/bookCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void testBookCar_Failure() throws Exception {
        // create sample data
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(1L);
        bookingDto.setUserId(1L);
        bookingDto.setFromDate(fromDate);
        bookingDto.setToDate(toDate);

        when(bookingService.bookCar(bookingDto)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/carRentals/bookCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isBadRequest());
    }
}
