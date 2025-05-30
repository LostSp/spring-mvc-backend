package com.example.spring_mvc_backend.service;
import com.example.spring_mvc_backend.mapper.MemberMapper;
import com.example.spring_mvc_backend.model.Member;
import com.example.spring_mvc_backend.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#^])[a-z\\d@$!%*?&#^]{12,20}$");
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("^[가-힣]{1,10}$");
    }


    public MemberService(MemberMapper memberMapper,
                         BCryptPasswordEncoder passwordEncoder,
                         JwtUtil jwtUtil) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Member registerMember(String id, String rawPassword, String username) {
        if (!isValidEmail(id)) {
            throw new RuntimeException("Invalid email format.");
        }
        if (memberMapper.selectMemberById(id) != null) {
            throw new RuntimeException("User already exists");
        }
        if (!isValidPassword(rawPassword)) {
            throw new RuntimeException("Password must be 12–20 characters, include lowercase letters, numbers, and special characters.");
        }
        if (!isValidUsername(username)) {
            throw new RuntimeException("Username must be in Korean and 1–10 characters.");
        }
        Member member = new Member();
        member.setId(id);
        member.setPassword(passwordEncoder.encode(rawPassword));
        member.setUsername(username);
        member.setRegistrationTime(LocalDateTime.now());

        memberMapper.insertMember(member);
        member.setPassword(null); // Don't expose password

        return member;
    }

    public String login(String id, String rawPassword) {
        if (!isValidEmail(id)) {
            throw new RuntimeException("Invalid email format.");
        }
        Member member = memberMapper.selectMemberById(id);
        if (member == null) {
            throw new RuntimeException("User not found");
        }

        System.out.println("Raw password input: " + rawPassword);
        System.out.println("Hashed password from DB: " + member.getPassword());
        System.out.println("Password matches: " + passwordEncoder.matches(rawPassword, member.getPassword()));

        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(member.getId());
    }



    public void updateMember(String id, String newPassword, String newUsername) {
        Member member = memberMapper.selectMemberById(id);
        if (member == null) {
            throw new RuntimeException("User not found");
        }

        if (newPassword != null) {
            if (!newPassword.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#^])[a-z\\d@$!%*?&#^]{12,20}$")) {
                throw new RuntimeException("Password must be 12–20 characters, include lowercase letters, numbers, and special characters.");
            }

            member.setPassword(passwordEncoder.encode(newPassword));
        }

        if (newUsername != null) {
            if (!newUsername.matches("^[가-힣]{1,10}$")) {
                throw new RuntimeException("Username must be in Korean and 1–10 characters.");
            }
            member.setUsername(newUsername);
        }

        memberMapper.updateMember(member);
    }
    public Member findById(String id) {
        return memberMapper.selectMemberById(id);
    }

}
