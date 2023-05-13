package com.example.springbootb2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private double price;
    private String name;
    private String manufacturer;
    private String description;
    private String type;
    @JsonProperty(access = READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDateTime;
    @JsonProperty(access = READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDateTime;
    private boolean isActive;
}
