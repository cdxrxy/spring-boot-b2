package com.example.springbootb2.exception;

public class ItemNotExistsException extends RuntimeException{
    public ItemNotExistsException(String message) {
        super(message);
    }
}
