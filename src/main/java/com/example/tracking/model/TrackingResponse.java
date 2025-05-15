package com.example.tracking.model;

import java.time.Instant;

import lombok.Data;

@Data
public class TrackingResponse {
    private String trackingNumber;
    private Instant createdAt;

    public TrackingResponse(String trackingNumber, Instant createdAt) {
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }
}