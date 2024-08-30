package com.example.user_microservice.manager.event.impl;

import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.event.EventParticipationManager;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.event.EventService;
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
public class EventParticipationManagerImpl implements EventParticipationManager {

    EventService eventService;
    UserService userService;
    UserMapper userMapper;
    UserValidation userValidation;

    @Override
    @Transactional
    public UserDto registerParticipant(Long eventId, Long userId) {
        Event event = eventService.get(eventId);
        User member = userService.get(userId);
        List<User> users = userService.findMemberByEventId(eventId);
        userValidation.checkUserInList(member, users);
        member.getEvents().add(event);
        return userMapper.toDto(member);
    }

    @Override
    @Transactional
    public UserDto unregisteredParticipation(Long eventId, Long userId) {
        Event event = eventService.get(eventId);
        User member = userService.get(userId);
        List<User> users = userService.findMemberByEventId(eventId);
        userValidation.checkUserNotInList(member, users);
        member.getEvents().remove(event);
        return userMapper.toDto(member);
    }

    @Override
    @Transactional
    public List<UserDto> getMembers(Long eventId) {
        eventService.get(eventId);
        List<User> members = userService.findMemberByEventId(eventId);
        return members.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Long countMembers(Long eventId) {
        eventService.get(eventId);
        return userService.countMembersByEventId(eventId);
    }
}
