package com.tuan.springsercurity.controller;

import com.tuan.springsercurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list-user")
@RequiredArgsConstructor
public class ListUserController {
    @Autowired
    UserService userService;
    @GetMapping()
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
}
