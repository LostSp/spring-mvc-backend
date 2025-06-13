package com.example.spring_mvc_backend.model;



import lombok.Data;


import java.time.LocalDateTime;

@Data
public class Member {
    private String id;            // email
    private String password;      // hashed password
    private String username;      // Korean username
    private LocalDateTime registrationTime;
}
