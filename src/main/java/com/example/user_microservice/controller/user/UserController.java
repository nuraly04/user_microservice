package com.example.user_microservice.controller.user;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.user.UserManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Paths.USER)
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserManager userManager;

    @PostMapping
    public UserDto create(@RequestBody @Valid UserCreateRequest userDto) {
        return userManager.create(userDto);
    }
}
