package com.example.bank_system.ApiResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}