package com.example.user_microservice.manager.mentorship.impl;

import com.example.user_microservice.dto.mentorship.UserDto;
import com.example.user_microservice.manager.mentorship.MentorshipManager;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipManagerImpl implements MentorshipManager {

    UserService userService;
    UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getMentees(Long mentorId) {
        User mentor = userService.get(mentorId);
        List<User> mentees = userService.findMenteesByMentorId(mentor.getId());
        return mentees.stream()
                .map(userMapper::toMenteeDto)
                .toList();
    }

    @Override
    @Transactional
    public List<UserDto> getMentors(Long menteeId) {
        User mentee = userService.get(menteeId);
        List<User> mentors = userService.findMentorsByMenteeId(mentee.getId());
        return mentors.stream()
                .map(userMapper::toMenteeDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteMentee(Long mentorId, Long menteeId) {
        User mentor = userService.get(mentorId);
        User mentee = userService.get(menteeId);
    }

}
