package com.example.springbootb2.controller;

import com.example.springbootb2.dto.UserDto;
import com.example.springbootb2.service.UserService;
import com.example.springbootb2.util.UserListMapper;
import com.example.springbootb2.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.userToUserDto(
                userService.createUser(
                        UserMapper.INSTANCE.userDtoToUser(userDto)
                )
        );
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return UserListMapper.INSTANCE.userListToUserDtoList(userService.getAllUsers());
    }

    @GetMapping("/{phone}")
    public UserDto getUserByPhone(@PathVariable String phone) {
        return UserMapper.INSTANCE.userToUserDto(userService.getUserByPhone(phone));
    }
}