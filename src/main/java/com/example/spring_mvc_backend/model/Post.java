package com.example.spring_mvc_backend.model;




import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Post {
    private Long id;
    private String title;
    private String content;
    private String username; // For response convenience
    private LocalDateTime createdAt;


    private String userId;

}

