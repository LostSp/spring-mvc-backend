package com.example.spring_mvc_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @Email
    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 12, max = 20)
    // Password regex: lowercase letters, special characters, numbers
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{12,20}$", message = "Password must contain lowercase, digit and special char")
    private String password;

    @NotBlank
    @Size(min = 1, max = 10)
    // For Korean characters, Unicode range check could be added but skipping for simplicity
    private String username;

    // getters and setters



    // Default constructor


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
