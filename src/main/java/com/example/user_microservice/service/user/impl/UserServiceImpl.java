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

import java.util.List;
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

    @Override
    @Transactional
    public void delete(User mentee, User mentor) {
        mentor.getMentees().remove(mentee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMenteesByMentorId(Long mentorId) {
        return userRepository.findMenteesByMentorId(mentorId);
    }

    @Override
    @Transactional
    public List<User> findMentorsByMenteeId(Long menteeId) {
        return userRepository.findMentorsByMenteeId(menteeId);
    }
}
