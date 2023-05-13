package com.example.springbootb2.service;

import com.example.springbootb2.exception.UserAlreadyExistsException;
import com.example.springbootb2.exception.UserNotExistsException;
import com.example.springbootb2.model.User;
import com.example.springbootb2.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepo userRepo;

    @Override
    public User createUser(User user) {
        if (userRepo.existsByPhone(user.getPhone())) {
            throw new UserAlreadyExistsException("User with that phone is already registered");
        }

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepo.findByPhone(phone)
                .orElseThrow(() -> new UserNotExistsException("There is no user with such phone"));
    }
}
