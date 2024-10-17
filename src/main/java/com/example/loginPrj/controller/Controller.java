package com.example.loginPrj.controller;

import com.example.loginPrj.dto.MemberDTO;
import com.example.loginPrj.service.MemberService;
import com.example.loginPrj.vo.MemberContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String mainPage(){
        return "/main";
    }

    @GetMapping("/loginPage")
    public String login(Model model, HttpSession session) {
        if(session.getAttribute("errorMessage") != null){
            model.addAttribute("errorMessage",session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }
        return "/login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDTO memberDTO, Model model, HttpServletRequest request){
        try{
            memberService.signup(memberDTO);
            request.login(memberDTO.getId(), memberDTO.getPw());
            return "redirect:/";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "/signup";
        }
    }

    @GetMapping("/user1")
    public String user1(Model model, @AuthenticationPrincipal MemberContext memberContext){
        model.addAttribute("member", memberContext.getMemberDTO());
        return "/user";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user2")
    public String user2(Model model, @AuthenticationPrincipal MemberContext memberContext){
        model.addAttribute("member", memberContext.getMemberDTO());
        return "/user";
    }

    @GetMapping("/admin1")
    public String admin1(Model model, @AuthenticationPrincipal MemberContext memberContext){
        model.addAttribute("member", memberContext.getMemberDTO());
        return "/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin2")
    public String admin2(Model model, @AuthenticationPrincipal MemberContext memberContext){
        model.addAttribute("member", memberContext.getMemberDTO());
        return "/admin";
    }

    @GetMapping("/denied")
    public String denied(){
        return "/denied";
    }
}
