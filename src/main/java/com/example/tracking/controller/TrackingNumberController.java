package com.example.tracking.controller;

import com.example.tracking.model.TrackingNumber;
import com.example.tracking.service.TrackingNumberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackingNumberController {

    private final TrackingNumberService service;

    public TrackingNumberController(TrackingNumberService service) {
        this.service = service;
    }

    @GetMapping("/next-tracking-number")
    public TrackingNumber getNextTrackingNumber(
        @RequestParam String origin_country_id,
        @RequestParam String destination_country_id,
        @RequestParam double weight,
        @RequestParam String created_at,
        @RequestParam String customer_id,
        @RequestParam String customer_name,
        @RequestParam String customer_slug) {
//        The parameters are currently unused.
//        They can be added into the logic for the creation of the tracking number.

        return service.generateTrackingNumber();
    }
}
