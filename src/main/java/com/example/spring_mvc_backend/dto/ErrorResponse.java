package com.example.spring_mvc_backend.dto;

public class ErrorResponse {
    public String error;

    public ErrorResponse(String message) {
        this.error = message;
    }
}
