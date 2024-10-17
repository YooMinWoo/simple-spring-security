package com.example.loginPrj;

import com.example.loginPrj.dto.MemberDTO;
import com.example.loginPrj.mappers.MemberMapper;
import com.example.loginPrj.service.MemberService;
import com.example.loginPrj.vo.Member;
import com.example.loginPrj.vo.MemberContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;

    @Test
    public void testFindById() {
        Member member = memberMapper.findId("testId"); // testId를 실제 DB에 존재하는 ID로 변경
        assertNull(member);
    }

    @Test
    @Transactional
    public void autoLogin() throws Exception{
        MemberDTO memberDTO = new MemberDTO("alsn123","유민우","asdasd","USER");
        memberService.signup(memberDTO);
        System.out.println("signup SUCCESS!!!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getPrincipal());

        MemberContext principal = (MemberContext) authentication.getPrincipal();
        System.out.println(principal.getMemberDTO().getId());

    }

}
