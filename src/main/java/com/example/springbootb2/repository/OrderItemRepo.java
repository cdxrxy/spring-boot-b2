package com.example.springbootb2.repository;

import com.example.springbootb2.model.OrderItem;
import com.example.springbootb2.model.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, OrderItemKey> {
}
