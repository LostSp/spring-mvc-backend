package com.example.spring_mvc_backend.service;



import com.example.spring_mvc_backend.mapper.CommentMapper;
import com.example.spring_mvc_backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Comment createComment(Long postId, String userId, String userName, String content) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        int rows = commentMapper.insertComment(comment);
        if (rows != 1) {
            throw new RuntimeException("Failed to insert comment");
        }
        return comment;
    }

    public List<Comment> getComments(Long postId, Long cursor, int limit) {
        return commentMapper.selectCommentsByPostId(postId, cursor, limit);
    }

    public void deleteComment(Long commentId) {
        int rows = commentMapper.deleteComment(commentId);
        if (rows != 1) {
            throw new RuntimeException("Failed to delete comment or comment not found");
        }
    }

    public Comment getCommentById(Long id) {
        return commentMapper.selectCommentById(id);
    }
}

