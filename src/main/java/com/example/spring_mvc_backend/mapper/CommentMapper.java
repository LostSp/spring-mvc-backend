package com.example.spring_mvc_backend.mapper;



import com.example.spring_mvc_backend.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // Insert new comment
    int insertComment(Comment comment);

    // Get list of comments for a post with cursor-based pagination
    // Cursor here is the comment id of the last item from previous fetch
    List<Comment> selectCommentsByPostId(
            @Param("postId") Long postId,
            @Param("cursor") Long cursor,
            @Param("limit") int limit);

    // Delete comment by id
    int deleteComment(@Param("id") Long id);

    // Optional: get comment by id to verify ownership during delete
    Comment selectCommentById(@Param("id") Long id);
}

