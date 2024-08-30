package com.example.user_microservice.controller.event;

import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.event.EventParticipationManager;
import com.example.user_microservice.utils.Paths;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.EVENT_PARTICIPATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventParticipationController {

    EventParticipationManager eventParticipationManager;

    @PostMapping("/{eventId}/registered/{userId}")
    public UserDto registerParticipant(
            @PathVariable("eventId") Long eventId,
            @PathVariable("userId") Long userId
    ) {
        return eventParticipationManager.registerParticipant(eventId, userId);
    }

    @PostMapping("/{eventId}/unregistered/{userId}")
    public UserDto unregistered(
            @PathVariable("eventId") Long eventId,
            @PathVariable("userId") Long userId
    ) {
        return eventParticipationManager.unregisteredParticipation(eventId, userId);
    }

    @GetMapping("/{eventId}/members")
    public List<UserDto> getMembers(@PathVariable("eventId") Long eventId) {
        return eventParticipationManager.getMembers(eventId);
    }

    @GetMapping("/{eventId}/members/count")
    public Long countMembers(@PathVariable("eventId") Long eventId) {
        return eventParticipationManager.countMembers(eventId);
    }
}
