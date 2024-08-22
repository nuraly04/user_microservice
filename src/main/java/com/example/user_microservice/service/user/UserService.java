package com.example.user_microservice.service.user;

import com.example.user_microservice.model.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User get(Long userId);
}
