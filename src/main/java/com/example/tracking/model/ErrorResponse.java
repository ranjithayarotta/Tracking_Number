package com.example.tracking.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String correlationId;

    public ErrorResponse(String errorCode, String message, String correlationId) {
        this.errorCode = errorCode;
        this.message = message;
        this.correlationId = correlationId;
    }
}