package com.example.spring_mvc_backend.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "ID must be a valid email")
    @NotBlank(message = "ID is required")
    private String id;

    @NotBlank(message = "Password is required")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
