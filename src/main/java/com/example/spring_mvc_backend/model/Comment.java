package com.example.spring_mvc_backend.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comment {
    private Long id;
    private String content;
    private String userId;
    private String username;
    private Long postId;
    private LocalDateTime createdAt;

}
