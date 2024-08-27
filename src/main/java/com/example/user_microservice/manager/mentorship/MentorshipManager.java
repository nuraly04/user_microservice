package com.example.user_microservice.manager.mentorship;

import com.example.user_microservice.dto.mentorship.UserDto;

import java.util.List;

public interface MentorshipManager {

    List<UserDto> getMentees(Long mentorId);

    List<UserDto> getMentors(Long menteeId);

    void deleteMentee(Long mentorId, Long menteeId);
}
