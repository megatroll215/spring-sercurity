package com.tuan.springsercurity.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController
{
    @GetMapping("hello")
public ResponseEntity<?> hello()
{
    return new ResponseEntity<>("hello", HttpStatus.OK);
}

//    @GetMapping("hello")
//    public ResponseEntity<?> hello()
//    {
//        return new ResponseEntity<>("hello", HttpStatus.OK);
//    }
}
