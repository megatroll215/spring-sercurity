package com.tuan.springsercurity.service;

import com.tuan.springsercurity.dtos.response.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UserDTO> getAll();
}
