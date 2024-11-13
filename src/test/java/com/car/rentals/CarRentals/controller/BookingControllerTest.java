
package com.car.rentals.CarRentals.controller;

import com.car.rentals.CarRentals.dto.BookingDto;
import com.car.rentals.CarRentals.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private BookingService bookingService;

        @Test
        @WithMockUser(username = "user", roles = {"USER"})
        public void testBookCar_Success() throws Exception {

            BookingDto bookingDto = new BookingDto();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Set the dates using java.util.Date
            bookingDto.setFromDate(dateFormat.parse("2024-11-13"));
            bookingDto.setToDate(dateFormat.parse("2024-11-16"));

            bookingDto.setUserId(1L);
            bookingDto.setCarId(1L);

            // Mock the booking service behavior
            when(bookingService.bookCar(any(BookingDto.class))).thenReturn(true);

            mockMvc.perform(post("/carRentals/bookCar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(bookingDto))
                            .with(csrf())) // Add CSRF token
                    .andExpect(status().isCreated());
        }

}

