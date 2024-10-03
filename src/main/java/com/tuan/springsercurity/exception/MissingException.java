package com.tuan.springsercurity.exception;

public class MissingException extends RuntimeException{
    public MissingException(String message) {
        super(message);
    }
}
