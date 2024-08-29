package com.example.user_microservice.service.event;

import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.model.event.Event;

import java.util.List;

public interface EventService {

    void deleteEvent(Event event);

    Event createEvent(Event event);

    Event get(Long eventId);

    List<Event> getEvents(EventFilterDto filterDto);
}
