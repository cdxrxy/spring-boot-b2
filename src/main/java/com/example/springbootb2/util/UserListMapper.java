package com.example.springbootb2.util;

import com.example.springbootb2.dto.UserDto;
import com.example.springbootb2.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserMapper.class)
public interface UserListMapper {
    UserListMapper INSTANCE = Mappers.getMapper(UserListMapper.class);

    List<User> userDtoListToUserList(List<UserDto> userDtoList);
    List<UserDto> userListToUserDtoList(List<User> userList);
}
