package com.example.spring_mvc_backend.service;



import com.example.spring_mvc_backend.mapper.UserLoginMapper;
import com.example.spring_mvc_backend.model.LoginRanking;
import com.example.spring_mvc_backend.model.UserLogin;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserLoginService {

    private final UserLoginMapper loginMapper;

    public UserLoginService(UserLoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public void recordLogin(String userId, String ipAddress) {
        UserLogin login = new UserLogin();
        login.setUserId(userId);

        login.setIpAddress(ipAddress);
        login.setLoginTime(LocalDateTime.now());

        loginMapper.insertLoginRecord(login);
    }

    public List<UserLogin> getRecentLogins(String userId) {
        return loginMapper.selectRecentLoginsByUserId(userId);
    }
    public List<LoginRanking> getWeeklyLoginRankings() {
        return loginMapper.selectWeeklyLoginRankings();
    }
}

