package com.example.springbootb2.repository;

import com.example.springbootb2.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByPhone(String phone);
    boolean existsByPhone(String phone);
}