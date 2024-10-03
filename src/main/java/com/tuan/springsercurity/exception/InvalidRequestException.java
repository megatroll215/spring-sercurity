package com.tuan.springsercurity.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class InvalidRequestException extends MethodArgumentNotValidException {
    public InvalidRequestException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }
}
