package com.tuan.springsercurity.service.impl;

import com.tuan.springsercurity.dtos.requets.RefreshTokenRequest;
import com.tuan.springsercurity.dtos.requets.user.SignInRequest;
import com.tuan.springsercurity.dtos.requets.user.SignUpRequest;
import com.tuan.springsercurity.dtos.response.JwtAuthenticationResponse;
import com.tuan.springsercurity.exception.CommonException;
import com.tuan.springsercurity.exception.MissingException;
import com.tuan.springsercurity.model.User;
import com.tuan.springsercurity.repository.UserRepository;
import com.tuan.springsercurity.service.AuthenticationService;
import com.tuan.springsercurity.service.JWTService;
import com.tuan.springsercurity.util.constant.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public User signup(SignUpRequest signUpRequest) throws Exception {
        Optional<UserDetails> checkEmail =  userRepository.findByEmail(signUpRequest.getEmail());
        if(checkEmail.isPresent())
        {
            throw new CommonException("user with this email is existed!");
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .firstname(signUpRequest.getFirstName())
                .secondname(signUpRequest.getLastName())
                .role(Role.USER)
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        var user =  userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new MissingException("user not found!"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request)
    {
        String userEmail = jwtService.extractUserName(request.getToken());
        var user =  userRepository.findByEmail(userEmail).orElseThrow(()->new MissingException("user not found!"));
        if(jwtService.isTokenValid(request.getToken(),user))
        {
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder()
                    .token(jwt)
                    .refreshToken(request.getToken())
                    .build();
        }
        return null;
    }
}