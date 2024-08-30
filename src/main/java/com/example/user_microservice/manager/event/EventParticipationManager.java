package com.example.user_microservice.manager.event;

import com.example.user_microservice.dto.user.UserDto;

import java.util.List;

public interface EventParticipationManager {

    UserDto registerParticipant(Long eventId, Long userId);

    UserDto unregisteredParticipation(Long eventId, Long userId);

    List<UserDto> getMembers(Long eventId);

    Long countMembers(Long eventId);
}
