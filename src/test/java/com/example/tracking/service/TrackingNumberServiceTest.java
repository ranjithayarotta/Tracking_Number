package com.example.tracking.service;

import com.example.tracking.repository.TrackingNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingNumberServiceTest {

    private TrackingNumberRepository repository;
    private TrackingNumberService service;

    @BeforeEach
    void setUp() {
        repository = mock(TrackingNumberRepository.class);
        service = new TrackingNumberService(repository);
    }

    @Test
    void testGenerateTrackingNumber_Success() {
        when(repository.existsByTrackingNumber(anyString())).thenReturn(false);
        when(repository.save(any(TrackingNumber.class))).thenAnswer(invocation -> invocation.getArgument(0));
        TrackingNumber result = service.generateTrackingNumber();
        assertNotNull(result);
        assertNotNull(result.getTrackingNumber());
        assertTrue(result.getTrackingNumber().length() >= 10 && result.getTrackingNumber().length() <= 16);
        assertNotNull(result.getCreatedAt());
        ArgumentCaptor<TrackingNumber> captor = ArgumentCaptor.forClass(TrackingNumber.class);
        verify(repository).save(captor.capture());
        TrackingNumber savedEntity = captor.getValue();
        assertEquals(result.getTrackingNumber(), savedEntity.getTrackingNumber());
        assertNotNull(savedEntity.getCreatedAt());
    }

    @Test
    void testGenerateUniqueTrackingNumber_RetryUntilUnique() {
        when(repository.existsByTrackingNumber(anyString()))
                .thenReturn(true)
                .thenReturn(false);
        when(repository.save(any(TrackingNumber.class))).thenAnswer(invocation -> invocation.getArgument(0));
        TrackingNumber result = service.generateTrackingNumber();
        assertNotNull(result.getTrackingNumber());
        verify(repository, atLeast(2)).existsByTrackingNumber(anyString());
    }
}
