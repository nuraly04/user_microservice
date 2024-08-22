package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.user.UserRepository;
import com.example.user_microservice.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(User.class, userId, "id"));
    }
}
