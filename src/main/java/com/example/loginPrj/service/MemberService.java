package com.example.loginPrj.service;

import com.example.loginPrj.dto.MemberDTO;
import com.example.loginPrj.mappers.MemberMapper;
import com.example.loginPrj.vo.Member;
import com.example.loginPrj.vo.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    public void signup(MemberDTO memberDTO) throws Exception {
        if(idCheck(memberDTO.getId())) throw new Exception("이미 존재하는 아이디입니다.");
        Member member = new Member(memberDTO.getId(), memberDTO.getName(), passwordEncoder.encode(memberDTO.getPw()), memberDTO.getRoles());
        memberMapper.signup(member);
    }

    public boolean idCheck(String id){
        Member member = memberMapper.findId(id);
        return member != null;
    }

    public Member findById(String id){
        return memberMapper.findId(id);
    }
}
