package com.example.spring_mvc_backend.dto;

import com.example.spring_mvc_backend.model.Comment;

import java.util.List;

public class CommentPageResponse {
    private List<Comment> comments;
    private Long nextCursor;

    public CommentPageResponse(List<Comment> comments, Long nextCursor) {
        this.comments = comments;
        this.nextCursor = nextCursor;
    }
    // getters and setters

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }
}

