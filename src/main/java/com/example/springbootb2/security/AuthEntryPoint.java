package com.example.springbootb2.security;

import com.example.springbootb2.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write(
                objectMapper.writeValueAsString(ErrorDto.builder()
                        .timestamp(new Date())
                        .status(SC_UNAUTHORIZED)
                        .error(UNAUTHORIZED.getReasonPhrase())
                        .path(request.getServletPath())
                        .message(authException.getMessage())
                        .build())
        );
    }
}
