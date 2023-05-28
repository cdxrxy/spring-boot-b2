package com.example.springbootb2.controller;

import com.example.springbootb2.dto.ErrorDto;
import com.example.springbootb2.exception.ItemNotExistsException;
import com.example.springbootb2.exception.UserAlreadyExistsException;
import com.example.springbootb2.exception.UserNotExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({UserNotExistsException.class, ItemNotExistsException.class})
    public ResponseEntity<?> handleNotFound(HttpServletRequest request, RuntimeException e) {
        return ResponseEntity.status(NOT_FOUND).body(
                ErrorDto.builder()
                        .timestamp(new Date())
                        .status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleConflict(HttpServletRequest request, RuntimeException e) {
        return ResponseEntity.status(CONFLICT).body(
                ErrorDto.builder()
                        .timestamp(new Date())
                        .status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(e.getMessage())
                        .build());
    }
}
