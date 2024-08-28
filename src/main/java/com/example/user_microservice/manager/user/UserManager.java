package com.example.user_microservice.manager.user;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;

public interface UserManager {

    UserDto create(UserCreateRequest userDto);
}
