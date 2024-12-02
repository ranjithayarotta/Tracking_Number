package com.example.tracking.service;

import com.example.tracking.model.TrackingNumber;
import com.example.tracking.repository.TrackingNumberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TrackingNumberService {

    private final TrackingNumberRepository repository;

    public TrackingNumberService(TrackingNumberRepository repository) {
        this.repository = repository;
    }

    public TrackingNumber generateTrackingNumber() {
        String trackingNumber = generateUniqueTrackingNumber();
        TrackingNumber entity = new TrackingNumber();
        entity.setTrackingNumber(trackingNumber);
        entity.setCreatedAt(LocalDateTime.now());
        return repository.save(entity);
    }

    private String generateUniqueTrackingNumber() {
        while (true) {
            // Generate a random alphanumeric tracking number of 16 characters
            String candidate = UUID.randomUUID().toString().replace("-", "").toUpperCase()
                                   .substring(0, ThreadLocalRandom.current().nextInt(10, 16));
            // Use a repository method to check for uniqueness
            if (!repository.existsByTrackingNumber(candidate)) {
                return candidate; // Return the unique tracking number
            }
        }
    }


}
