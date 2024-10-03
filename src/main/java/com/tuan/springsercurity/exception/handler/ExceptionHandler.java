package com.tuan.springsercurity.exception.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tuan.springsercurity.dtos.error.Error;
import com.tuan.springsercurity.exception.CommonException;
import com.tuan.springsercurity.exception.InternalException;
import com.tuan.springsercurity.exception.MissingException;
import com.tuan.springsercurity.util.constant.CommonConstant;
import com.tuan.springsercurity.util.Creator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({CommonException.class})
    public ResponseEntity<Error> commonEx(Exception ex)
    {
        Error error = Error.builder()
                .date(new Date(System.currentTimeMillis()))
                .error(CommonConstant.ERROR)
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({InternalException.class})
    public ResponseEntity<Error> internalEx(Exception ex)
    {
        Error error = Error.builder()
                .date(new Date(System.currentTimeMillis()))
                .error(CommonConstant.ERROR)
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MissingException.class})
    public ResponseEntity<Error> missingEx(Exception ex)
    {
        Error error = Error.builder()
                .date(new Date(System.currentTimeMillis()))
                .error(CommonConstant.ERROR)
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> invalidRequestEx(MethodArgumentNotValidException ex)
    {
        ObjectNode errors = Creator.createObject();
         ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(x->
                {
                    errors.put(x.getField(),x.getDefaultMessage());
                });
        Error error = Error.builder()
                .date(new Date(System.currentTimeMillis()))
                .error(CommonConstant.ERROR)
                .message(getErrorsMap(errors))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    private Map<String, ObjectNode> getErrorsMap(ObjectNode error) {
        Map<String, ObjectNode> errorResponse = new HashMap<>();
        errorResponse.put(CommonConstant.ERROR, error);
        return errorResponse;
    }

}
