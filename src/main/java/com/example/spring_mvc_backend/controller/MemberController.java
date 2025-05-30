package com.example.spring_mvc_backend.controller;



import com.example.spring_mvc_backend.dto.*;
import com.example.spring_mvc_backend.service.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_mvc_backend.model.Member;
import com.example.spring_mvc_backend.service.MemberService;
import com.example.spring_mvc_backend.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    private final UserLoginService userLoginService;

    public MemberController(MemberService memberService, JwtUtil jwtUtil, UserLoginService userLoginService) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
        this.userLoginService = userLoginService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        try {
            Member member = memberService.registerMember(req.getId(), req.getPassword(), req.getUsername());
            return ResponseEntity.ok().body(new SignupResponse(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }




    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello,hroo hi and hi");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req, HttpServletRequest request) {
        try {
            String token = memberService.login(req.getId(), req.getPassword());
            //  Record login info (user ID and IP)
            String ipAddress = request.getRemoteAddr();
            userLoginService.recordLogin(req.getId(), ipAddress);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }


    @PatchMapping("/member")
    public ResponseEntity<?> updateMember(@RequestHeader("Authorization") String authHeader,
                                          @RequestBody UpdateMemberRequest req) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String id = jwtUtil.getUserIdFromToken(token);
            // validate & extract email

            memberService.updateMember(id, req.getPassword(), req.getUsername());
            return ResponseEntity.ok().body("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }


}
