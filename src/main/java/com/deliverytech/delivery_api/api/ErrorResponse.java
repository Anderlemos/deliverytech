package com.deliverytech.delivery_api.api;

import java.time.LocalDateTime;

/**
 * Classe usada para padronizar respostas de erro.
 */
public class ErrorResponse {

    private boolean success;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String error, String message) {
        this.success = false;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}