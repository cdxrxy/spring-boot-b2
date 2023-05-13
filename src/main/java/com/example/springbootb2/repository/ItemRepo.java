package com.example.springbootb2.repository;

import com.example.springbootb2.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
