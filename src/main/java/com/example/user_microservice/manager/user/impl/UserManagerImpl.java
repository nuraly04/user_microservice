package com.example.user_microservice.manager.user.impl;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.dto.user.UserFilterDto;
import com.example.user_microservice.manager.user.UserManager;
import com.example.user_microservice.mapper.contact.ContactMapper;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.contact.Contact;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.contact.ContactService;
import com.example.user_microservice.service.reference.RefCommonReferenceService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.validation.user.UserValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserManagerImpl implements UserManager {

    UserService userService;
    UserMapper userMapper;
    ContactMapper contactMapper;
    ContactService contactService;
    RefCommonReferenceService referenceService;
    UserValidation userValidation;

    @Override
    @Transactional
    public UserDto create(UserCreateRequest userDto) {
        userValidation.checkEmail(userDto.getEmail());
        User user = userMapper.toEntity(userDto, referenceService.get(userDto.getCityId()));
        userService.save(user);
        List<Contact> contacts = userDto.getContacts().stream()
                .map(contact -> contactMapper.toEntity(contact, user))
                .toList();
        contacts.forEach(contactService::saveOrUpdate);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto get(Long userId) {
        User user = userService.get(userId);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public List<UserDto> getUsers(UserFilterDto filterDto) {
        List<User> users = userService.getUsers(filterDto);
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }
}
