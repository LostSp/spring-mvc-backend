package com.example.spring_mvc_backend.model;

import lombok.Data;

@Data
public class LoginRanking {
    private String username;
    private int loginCount;
    private int rank;


}

