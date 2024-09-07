package com.example.user_microservice.manager.user;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.dto.user.UserFilterDto;

import java.util.List;

public interface UserManager {

    UserDto create(UserCreateRequest userDto);

    UserDto get(Long userId);

    List<UserDto> getUsers(UserFilterDto filterDto);
}
