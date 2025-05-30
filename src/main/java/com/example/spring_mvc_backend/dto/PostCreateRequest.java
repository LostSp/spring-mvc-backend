package com.example.spring_mvc_backend.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostCreateRequest {

    @NotBlank(message = "Title must not be empty")
    @Size(min = 1, max = 30, message = "Title must be between 1 and 30 characters")
    private String title;

    @NotBlank(message = "Content must not be empty")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    private String content;

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
