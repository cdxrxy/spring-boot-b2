package com.example.springbootb2.service;

import com.example.springbootb2.model.Order;

import java.util.List;

public interface IOrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
}
