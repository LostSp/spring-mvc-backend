package com.example.spring_mvc_backend.controller;

import com.example.spring_mvc_backend.dto.CommentCreateRequest;

import com.example.spring_mvc_backend.model.Comment;
import com.example.spring_mvc_backend.model.Member;
import com.example.spring_mvc_backend.service.CommentService;
import com.example.spring_mvc_backend.service.MemberService;
import com.example.spring_mvc_backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    public CommentController(CommentService commentService, JwtUtil jwtUtil, MemberService memberService) {
        this.commentService = commentService;
        this.jwtUtil = jwtUtil;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateRequest commentRequest, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            String userId = jwtUtil.getUserIdFromToken(token);
            Member member = memberService.findById(userId);
            if (member == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            String username = member.getUsername();

            commentService.createComment(commentRequest.getPostId(), userId, username, commentRequest.getContent());


            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating comment: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listComments(@RequestParam Long postId) {
        try {
            List<Comment> comments = commentService.getCommentsByPostId(postId);  // use correct method name
            // If you want to map Comment to CommentResponse, do that here
            // For now, returning List<Comment> directly
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching comments");
        }
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            String userId = jwtUtil.getUserIdFromToken(token);

            // Optional: check if comment belongs to user before deleting
            // You may need to add a method in CommentService or Mapper to get comment by id
            // and verify comment.getUserId().equals(userId) for authorization
            Comment comment = commentService.getCommentById(commentId);
            if (comment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
            }
            if (!comment.getUserId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only delete your own comments");
            }


            commentService.deleteComment(commentId);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting comment: " + e.getMessage());
        }
    }




}
