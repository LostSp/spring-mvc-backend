package com.example.spring_mvc_backend.service;

import com.example.spring_mvc_backend.mapper.CommentMapper;
import com.example.spring_mvc_backend.model.Comment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Comment createComment(Long postId, String userId, String username, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUsername(username);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        commentMapper.insertComment(comment);
        return comment;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.selectCommentsByPostId(postId);
    }

    public void deleteComment(Long commentId) {
        int deleted = commentMapper.deleteComment(commentId);
        if (deleted == 0) {
            throw new IllegalArgumentException("Comment not found or could not be deleted");
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }




}
