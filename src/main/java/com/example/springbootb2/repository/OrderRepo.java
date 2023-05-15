package com.example.springbootb2.repository;

import com.example.springbootb2.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "user")
    List<Order> findAll();
}
