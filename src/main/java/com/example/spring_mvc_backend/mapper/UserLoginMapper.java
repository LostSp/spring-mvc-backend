package com.example.spring_mvc_backend.mapper;



import com.example.spring_mvc_backend.model.LoginRanking;
import com.example.spring_mvc_backend.model.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginMapper {
    void insertLoginRecord(UserLogin login);
    List<UserLogin> selectRecentLoginsByUserId(String userId);
    List<LoginRanking> selectWeeklyLoginRankings();
}

