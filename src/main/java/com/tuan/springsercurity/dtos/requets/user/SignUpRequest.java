package com.tuan.springsercurity.dtos.requets.user;

import com.tuan.springsercurity.util.constant.RegexConstant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String firstName;
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String lastName;
    @Pattern(regexp = RegexConstant.EMAIL_REGEX,message = "email is not valid")
    private String email;
    @Pattern(regexp = RegexConstant.PASSWORD_REGEX,message = "password is not valid")
    private String password;
}