package com.example.tracking.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class TrackingRequest {
    @NotBlank(message = "Origin country ID is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Origin country ID must be 2-letter ISO code")
    private String originCountryId;

    @NotBlank(message = "Destination country ID is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Destination country ID must be 2-letter ISO code")
    private String destinationCountryId;

    @DecimalMin(value = "0.001", message = "Weight must be at least 0.001 kg")
    @DecimalMax(value = "999.999", message = "Weight cannot exceed 999.999 kg")
    private BigDecimal weight;

    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    private String customerName;

    @NotBlank(message = "Customer slug is required")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Invalid slug format")
    private String customerSlug;
}
