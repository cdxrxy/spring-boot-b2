package com.example.springbootb2.service;

import com.example.springbootb2.dto.UserDto;
import com.example.springbootb2.model.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserByPhone(String phone);
}