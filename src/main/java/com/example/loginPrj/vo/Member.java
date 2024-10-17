package com.example.loginPrj.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Member {
    private final String id;
    private final String name;
    private final String pw;
    private final String roles;

}
