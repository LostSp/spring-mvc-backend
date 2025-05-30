package com.example.spring_mvc_backend.controller;

import com.example.spring_mvc_backend.dto.PostResponse;
import com.example.spring_mvc_backend.model.Post;
import com.example.spring_mvc_backend.service.PostService;
import com.example.spring_mvc_backend.util.JwtUtil;
import com.example.spring_mvc_backend.dto.PostCreateRequest;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;

    public PostController(PostService postService, JwtUtil jwtUtil) {
        this.postService = postService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateRequest postRequest, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            String userIdStr = jwtUtil.getUserIdFromToken(token);
           // Long userId = Long.parseLong(userIdStr); // If userId is email string, skip parsing to Long

            Post post = new Post();
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setUserId(userIdStr); // or Long if you use numeric ID

            Post createdPost = postService.createPost(post);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post");
        }
    }


   @GetMapping
   public ResponseEntity<?> listPosts(@RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
       try {
           String authHeader = request.getHeader("Authorization");
           if (authHeader == null || !authHeader.startsWith("Bearer ")) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
           }

           String token = authHeader.substring(7);
           if (!jwtUtil.validateToken(token)) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
           }

           Map<String, Object> result = postService.listPosts(page);
           return ResponseEntity.ok(result);
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching posts");
       }
   }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            PostResponse post = postService.getPostDetail(postId);
            return ResponseEntity.ok(post);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching post details");
        }
    }


}
