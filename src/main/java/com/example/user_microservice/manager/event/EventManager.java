package com.example.user_microservice.manager.event;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.dto.event.EventFilterDto;

import java.util.List;

public interface EventManager {

    void deleteEvent(Long eventId);

    EventDto updateEvent(Long eventId, EventDto eventDto);

    EventDto createEvent(Long ownerId, EventDto eventDto);

    EventDto getEvent(Long eventId);

    List<EventDto> getEvents(EventFilterDto filterDto);

    List<EventDto> getOwnerEvents(Long ownerId);

    List<EventDto> getMemberEvents(Long memberId);
}
