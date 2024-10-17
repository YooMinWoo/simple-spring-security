package com.example.loginPrj.security.service;

import com.example.loginPrj.dto.MemberDTO;
import com.example.loginPrj.mappers.MemberMapper;
import com.example.loginPrj.service.MemberService;
import com.example.loginPrj.vo.Member;
import com.example.loginPrj.vo.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findId(username);
        if(member == null){
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(member.getRoles()));

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setPw(member.getPw());
        memberDTO.setName(member.getName());
        memberDTO.setRoles(member.getRoles());

        return new MemberContext(memberDTO,authorities);
    }
}
