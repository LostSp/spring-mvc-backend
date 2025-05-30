package com.example.spring_mvc_backend.mapper;

import com.example.spring_mvc_backend.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertComment(Comment comment);

    List<Comment> selectCommentsByPostId(@Param("postId") Long postId);

    int deleteComment(@Param("id") Long id);
    Comment selectCommentById(@Param("id") Long id);


}
