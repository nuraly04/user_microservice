package com.example.user_microservice.manager.mentorship.impl;

import com.example.user_microservice.dto.mentorship.MentorshipFilterDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestAddDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestDto;
import com.example.user_microservice.dto.user.RemoveUsers;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.mentorship.MentorshipManager;
import com.example.user_microservice.mapper.mentorship.MentorshipMapper;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.mentorship.MentorshipService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import com.example.user_microservice.validation.mentorship.MentorshipValidation;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.user_microservice.model.mentorship.QMentorshipRequest.mentorshipRequest;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipManagerImpl implements MentorshipManager {

    UserService userService;
    UserMapper userMapper;
    MentorshipMapper mentorshipMapper;
    MentorshipService mentorshipService;
    MentorshipValidation mentorshipValidation;

    @Override
    @Transactional
    public void requestMentorship(Long menteeId, MentorshipRequestAddDto requestDto) {
        User mentee = userService.get(menteeId);
        User mentor = userService.get(requestDto.getMentorId());
        mentorshipValidation.validateAvailableRequest(mentee, mentor);
        mentorshipService.requestMentorship(mentorshipMapper.toEntity(mentee, mentor, requestDto));
    }

    @Override
    @Transactional
    public void acceptRequest(Long mentorId, Long requestId) {
        userService.get(mentorId);
        MentorshipRequest request = mentorshipService.get(requestId);
        mentorshipService.updateStatus(request, RequestStatusEnum.ACCEPTED);
    }

    @Override
    @Transactional
    public void rejectRequest(Long mentorId, Long requestId) {
        userService.get(mentorId);
        MentorshipRequest request = mentorshipService.get(requestId);
        mentorshipService.updateStatus(request, RequestStatusEnum.REJECTED);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getMentees(Long mentorId) {
        User mentor = userService.get(mentorId);
        List<User> mentees = userService.findMenteesByMentorId(mentor.getId());
        return mentees.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<MentorshipRequestDto> getRequests(Long mentorId, MentorshipFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        search(predicate, filterDto);
        List<MentorshipRequest> requests = mentorshipService.getRequests(predicate);
        return requests.stream()
                .map(request -> mentorshipMapper.toDto(userMapper.toDto(request.getMentee()), request))
                .toList();
    }

    @Override
    @Transactional
    public List<UserDto> getMentors(Long menteeId) {
        User mentee = userService.get(menteeId);
        List<User> mentors = userService.findMentorsByMenteeId(mentee.getId());
        return mentors.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteMentee(Long mentorId, RemoveUsers users) {
        User mentor = userService.get(mentorId);
        List<User> mentees = userService.findUsersByIds(users.getUserIds());
        userService.deleteMentorshipUsers(mentees, mentor);
    }

    @Override
    @Transactional
    public void deleteMentor(Long menteeId, RemoveUsers users) {
        User mentee = userService.get(menteeId);
        List<User> mentors = userService.findUsersByIds(users.getUserIds());
        userService.deleteMentorshipUsers(mentors, mentee);
    }

    private void search(BooleanBuilder predicate, MentorshipFilterDto filterDto) {
        if (nonNull(filterDto.getContent())) {
            predicate.and(mentorshipRequest.content.containsIgnoreCase(filterDto.getContent()));
        }
        if (nonNull(filterDto.getMenteeId())) {
            predicate.and(mentorshipRequest.mentee.id.eq(filterDto.getMenteeId()));
        }
        if (nonNull(filterDto.getStatus())) {
            predicate.and(mentorshipRequest.status.eq(filterDto.getStatus()));
        }
    }
}
