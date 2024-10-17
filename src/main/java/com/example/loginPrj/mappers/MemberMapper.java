package com.example.loginPrj.mappers;

import com.example.loginPrj.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member findId(String id);
    void signup(Member member);
}
