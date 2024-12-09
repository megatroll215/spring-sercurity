package com.tuan.springsercurity.dtos.requets;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}