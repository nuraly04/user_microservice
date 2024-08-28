package com.example.user_microservice.manager.mentorship;

import com.example.user_microservice.dto.mentorship.MentorshipFilterDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestAddDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestDto;
import com.example.user_microservice.dto.user.RemoveUsers;
import com.example.user_microservice.dto.user.UserDto;

import java.util.List;

public interface MentorshipManager {

    void requestMentorship(Long menteeId, MentorshipRequestAddDto requestDto);

    void acceptRequest(Long mentorId, Long requestId);

    void rejectRequest(Long mentorId, Long requestId);

    List<UserDto> getMentees(Long mentorId);

    List<MentorshipRequestDto> getRequests(Long mentorId, MentorshipFilterDto filterDto);

    List<UserDto> getMentors(Long menteeId);

    void deleteMentee(Long mentorId, RemoveUsers users);

    void deleteMentor(Long mentorId, RemoveUsers users);
}
