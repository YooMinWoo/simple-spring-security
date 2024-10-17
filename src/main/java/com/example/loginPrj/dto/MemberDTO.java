package com.example.loginPrj.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private String pw;
    private String roles;

    public MemberDTO(String id, String name, String pw, String roles) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.roles = roles;
    }
}
