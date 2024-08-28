package com.example.user_microservice.service.user;

import com.example.user_microservice.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User get(Long userId);

    User save(User user);

    void deleteMentorshipUsers(List<User> mentees, User mentor);

    List<User> findMenteesByMentorId(Long mentorId);

    List<User> findUsersByIds(List<Long> userIds);

    List<User> findMentorsByMenteeId(Long menteeId);
}
