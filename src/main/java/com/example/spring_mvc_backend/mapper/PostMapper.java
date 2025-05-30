package com.example.spring_mvc_backend.mapper;





import com.example.spring_mvc_backend.dto.PostResponse;
import com.example.spring_mvc_backend.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(Post post);
    Post findPostById(Long id); // New method
    List<Post> findPosts(@Param("offset") int offset, @Param("limit") int limit);
    int countPosts();
    PostResponse getPostById(Long postId);


}


