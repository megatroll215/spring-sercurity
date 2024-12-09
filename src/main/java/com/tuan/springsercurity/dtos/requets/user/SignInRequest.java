package com.tuan.springsercurity.dtos.requets.user;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}