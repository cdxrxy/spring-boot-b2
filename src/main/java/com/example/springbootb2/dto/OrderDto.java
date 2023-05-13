package com.example.springbootb2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    @JsonProperty(access = READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDateTime;
    private double totalPrice;
    private Long userId;
    private List<OrderItemDto> orderItems;
}
