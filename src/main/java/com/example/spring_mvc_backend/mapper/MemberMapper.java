package com.example.spring_mvc_backend.mapper;



import org.apache.ibatis.annotations.Mapper;
import com.example.spring_mvc_backend.model.Member;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int insertMember(Member member);
    int updateMember(Member member);

    Member selectMemberById(@Param("id") String id);
    Member findByEmail(String email); //
}
