package com.example.spring_mvc_backend.service;





import com.example.spring_mvc_backend.dto.PostResponse;
import com.example.spring_mvc_backend.mapper.PostMapper;
import com.example.spring_mvc_backend.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public Post createPost(Post post) {
        // Validate title
        if (!StringUtils.hasText(post.getTitle()) || post.getTitle().length() > 30) {
            throw new IllegalArgumentException("Title must be 1-30 characters");
        }

        // Validate content
        if (!StringUtils.hasText(post.getContent()) || post.getContent().length() > 1000) {
            throw new IllegalArgumentException("Content must be 1-1000 characters");
        }

        // Insert post
        postMapper.insertPost(post); // post.id now has value

        // Fetch full post with createdAt and username
        return postMapper.findPostById(post.getId());
    }
    public Map<String, Object> listPosts(int page) {
        int pageSize = 20;
        int offset = (page - 1) * pageSize;

        List<Post> posts = postMapper.findPosts(offset, pageSize);
        int totalCount = postMapper.countPosts();

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("totalCount", totalCount);
        return result;
    }

    public PostResponse getPostDetail(Long postId) {
        PostResponse post = postMapper.getPostById(postId);
        if (post == null) {
            throw new NoSuchElementException("Post not found with ID: " + postId);
        }
        return post;
    }

}


