package com.example.spring_mvc_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentCreateRequest {

    @NotNull(message = "Post ID must not be null")
    private Long postId;

    @NotBlank(message = "Content must not be empty")
    @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
    private String content;


    public Long getPostId() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
