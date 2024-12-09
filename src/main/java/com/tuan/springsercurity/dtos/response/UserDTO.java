package com.tuan.springsercurity.dtos.response;

import com.tuan.springsercurity.util.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String secondname;
    private String email;
}