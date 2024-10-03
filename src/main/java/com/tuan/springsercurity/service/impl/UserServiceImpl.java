package com.tuan.springsercurity.service.impl;

import com.tuan.springsercurity.dtos.response.UserDTO;
import com.tuan.springsercurity.repository.UserRepository;
import com.tuan.springsercurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService
{

    private final UserRepository userRepository;



    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAll()
    {
        return userRepository.findAllUser();
    }

}
