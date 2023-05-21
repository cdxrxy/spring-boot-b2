package com.example.springbootb2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String phone;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private List<OrderDto> orders;
    private String role;
    private boolean isActive;
}