package com.example.springbootb2.controller;

import com.example.springbootb2.dto.OrderDto;
import com.example.springbootb2.service.IOrderService;
import com.example.springbootb2.util.OrderListMapper;
import com.example.springbootb2.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return OrderMapper.INSTANCE.orderToOrderDto(
                orderService.createOrder(
                        OrderMapper.INSTANCE.orderDtoToOrder(orderDto)
                )
        );
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return OrderListMapper.INSTANCE.orderListToOrderDtoList(orderService.getAllOrders());
    }
}
