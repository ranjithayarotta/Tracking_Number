package com.example.tracking.controller;

import com.example.tracking.service.TrackingNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrackingNumberController.class)
class TrackingNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingNumberService service;

    @Test
    void testGetNextTrackingNumber() throws Exception {
        TrackingNumber trackingNumber = new TrackingNumber();
        trackingNumber.setTrackingNumber("ABC123456789XYZ");
        trackingNumber.setCreatedAt(LocalDateTime.now());

        when(service.generateTrackingNumber()).thenReturn(trackingNumber);
        mockMvc.perform(get("/next-tracking-number")
                .param("origin_country_id", "US")
                .param("destination_country_id", "IN")
                .param("weight", "1.2")
                .param("created_at", "2024-05-10T10:15:30")
                .param("customer_id", "CUST123")
                .param("customer_name", "Test Customer")
                .param("customer_slug", "test-customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value("ABC123456789XYZ"))
                .andExpect(jsonPath("$.createdAt").exists());
    }
}
