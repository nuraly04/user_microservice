package com.example.user_microservice.service.event;

import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.user.User;

import java.util.List;

public interface EventService {

    void deleteEvent(Event event);
    Event get(Long eventId);

    Event saveOrUpdate(Event event);

    List<Event> findByOwner(User owner);

    List<Event> findByMember(User owner);

    List<Event> getEvents(EventFilterDto filterDto);
}
