package com.example.springbootb2.controller;

import com.example.springbootb2.dto.ErrorDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        int statusCode = Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());

        return ResponseEntity.status(statusCode).body(ErrorDto.builder()
                .timestamp(new Date())
                .status(statusCode)
                .path(request.getServletPath())
                .message(request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString())
                .build());
    }
}
