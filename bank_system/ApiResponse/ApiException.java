package com.example.bank_system.ApiResponse;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}