package com.example.spring_mvc_backend.controller;

import com.example.spring_mvc_backend.model.LoginRanking;
import com.example.spring_mvc_backend.model.UserLogin;
import com.example.spring_mvc_backend.service.UserLoginService;
import com.example.spring_mvc_backend.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login-records")
public class UserLoginController {

    private final UserLoginService loginService;
    private final JwtUtil jwtUtil;

    public UserLoginController(UserLoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    // GET /api/login-records/{userId}
    @GetMapping("/{userId}")
    public List<UserLogin> getLoginRecords(@PathVariable String userId) {
        return loginService.getRecentLogins(userId);
    }

    //  Weekly rankings with JWT check
    @GetMapping("/weekly-rankings")
    public ResponseEntity<?> getWeeklyRankings(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String userId = jwtUtil.getUserIdFromToken(token);  // Just to validate token

            List<LoginRanking> rankings = loginService.getWeeklyLoginRankings();
            return ResponseEntity.ok(rankings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token: " + e.getMessage());
        }
    }
}
