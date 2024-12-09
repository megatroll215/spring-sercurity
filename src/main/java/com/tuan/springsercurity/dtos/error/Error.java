package com.tuan.springsercurity.dtos.error;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Error {
    private Object message;
    private String error;
    private int status;
    private Date date;
}