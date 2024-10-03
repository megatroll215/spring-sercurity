package com.tuan.springsercurity.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
