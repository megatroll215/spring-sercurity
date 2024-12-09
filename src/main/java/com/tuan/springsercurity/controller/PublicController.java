package com.tuan.springsercurity.controller;

import com.tuan.springsercurity.dtos.requets.RefreshTokenRequest;
import com.tuan.springsercurity.dtos.requets.user.SignInRequest;
import com.tuan.springsercurity.dtos.requets.user.SignUpRequest;
import com.tuan.springsercurity.exception.MissingException;
import com.tuan.springsercurity.repository.UserRepository;
import com.tuan.springsercurity.service.AuthenticationService;
import com.tuan.springsercurity.service.JWTService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class PublicController
{

    private final JWTService jwtService;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    @GetMapping("hello")
    public ResponseEntity<?> hello()
    {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?>  signup(@RequestBody@Valid SignUpRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<?>  signin(@RequestBody@Valid SignInRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signIn(request));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?>  refreshToken(@RequestBody@Valid RefreshTokenRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.refreshToken(request));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me()
    {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }


}