package com.example.springbootb2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
    private String message;
}
