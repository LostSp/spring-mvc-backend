package com.example.spring_mvc_backend.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class UserLogin {
    private Long id;
    private String userId;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
    private String ipAddress;
}

