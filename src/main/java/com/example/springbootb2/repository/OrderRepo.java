package com.example.springbootb2.repository;

import com.example.springbootb2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
