package com.tuan.springsercurity.service;

import com.tuan.springsercurity.dtos.requets.RefreshTokenRequest;
import com.tuan.springsercurity.dtos.requets.user.SignInRequest;
import com.tuan.springsercurity.dtos.requets.user.SignUpRequest;
import com.tuan.springsercurity.dtos.response.JwtAuthenticationResponse;
import com.tuan.springsercurity.model.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest) throws Exception;

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);
}
