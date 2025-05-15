package com.example.tracking.service;

import com.example.tracking.model.TrackingRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TrackingNumberService {
    private static final String ALPHANUMERIC = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int RANDOM_STRING_LENGTH = 6;
    private static final int MAX_TRACKING_LENGTH = 16;

    private final RedisTemplate<String, String> redisTemplate;

    public TrackingNumberService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public String generateTrackingNumber(TrackingRequest request) {
        long sequenceNumber = getNextSequence();
        String countryPrefix = request.getOriginCountryId() + request.getDestinationCountryId();
        String randomComponent = generateRandomString();
        String sequenceComponent = String.format("%06d", sequenceNumber % 999999);

        String baseNumber = countryPrefix + randomComponent + sequenceComponent;
        String checksum = calculateChecksum(baseNumber);

        String trackingNumber = (baseNumber + checksum).substring(0, Math.min(MAX_TRACKING_LENGTH, (baseNumber + checksum).length()));

        log.info("Generated tracking number: {}", trackingNumber);
        return trackingNumber;
    }

    private long getNextSequence() {
        String sequenceKey = "tracking:sequence";
        return redisTemplate.opsForValue().increment(sequenceKey, 1);
    }

    private String generateRandomString() {
        return RandomStringUtils.random(RANDOM_STRING_LENGTH, ALPHANUMERIC);
    }

    private String calculateChecksum(String input) {
        int sum = input.chars().sum();
        return String.valueOf(sum % 10);
    }
}