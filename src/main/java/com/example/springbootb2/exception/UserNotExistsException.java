package com.example.springbootb2.exception;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException(String message) {
        super(message);
    }
}
