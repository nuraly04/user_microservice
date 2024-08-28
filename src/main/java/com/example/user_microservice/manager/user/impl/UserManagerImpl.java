package com.example.user_microservice.manager.user.impl;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.user.UserManager;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.reference.RefCommonReferenceService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.utils.enums.RefCommonReferenceTypeEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserManagerImpl implements UserManager {

    UserService userService;
    UserMapper userMapper;
    RefCommonReferenceService referenceService;

    @Override
    @Transactional
    public UserDto create(UserCreateRequest userDto) {
        User user = userMapper.toEntity(userDto);
        user.setCity(referenceService.get(userDto.getCityId()));
        user.setDateOfBirth(userDto.getDateOfBirth());
        userService.save(user);
        return userMapper.toDto(user);
    }
}
