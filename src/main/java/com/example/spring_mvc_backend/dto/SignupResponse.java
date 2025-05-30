package com.example.spring_mvc_backend.dto;

import com.example.spring_mvc_backend.model.Member;

public class SignupResponse {
    public String id;
    public String username;
    public String registrationTime;

    public SignupResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.registrationTime = member.getRegistrationTime().toString();
    }
}
