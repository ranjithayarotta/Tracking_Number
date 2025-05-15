package com.example.tracking.controller;

import com.example.tracking.model.TrackingRequest;
import com.example.tracking.model.TrackingResponse;
import com.example.tracking.service.TrackingNumberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TrackingController {
    private final TrackingNumberService trackingNumberService;

    public TrackingController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping("/tracking-numbers/next")
    public ResponseEntity<TrackingResponse> generateTrackingNumber(
        @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId,
        @Valid TrackingRequest request) {

        correlationId = correlationId == null ? UUID.randomUUID().toString() : correlationId;
        MDC.put("correlationId", correlationId);

        log.info("Received tracking number request for customer: {}", request.getCustomerName());

        try {
            String trackingNumber = trackingNumberService.generateTrackingNumber(request);
            TrackingResponse response = new TrackingResponse(trackingNumber, Instant.now());

            log.info("Successfully generated tracking number");
            return ResponseEntity.ok()
                                 .header("X-Correlation-ID", correlationId)
                                 .body(response);
        } finally {
            MDC.remove("correlationId");
        }
    }
}