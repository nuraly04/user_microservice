package com.example.user_microservice.controller.event;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.manager.event.EventManager;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.EVENT)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {

    EventManager eventManager;

    @PostMapping()
    public EventDto createEvent(
            @RequestParam Long userId,
            @RequestBody @Valid EventDto eventDto
    ) {
        return eventManager.createEvent(userId, eventDto);
    }

    @GetMapping("/{eventId}")
    public EventDto getEvent(@PathVariable("eventId") Long eventId) {
        return eventManager.getEvent(eventId);
    }

    @GetMapping("/list")
    public List<EventDto> getEvents(@RequestBody EventFilterDto filterDto) {
        return eventManager.getEvents(filterDto);
    }

    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        eventManager.deleteEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public EventDto updateEvent(
            @PathVariable("eventId") Long eventId,
            @RequestBody EventDto eventDto
    ) {
        return eventManager.updateEvent(eventId, eventDto);
    }

    @GetMapping("/{ownerId}")
    public List<EventDto> getOwnerEvents(@PathVariable("ownerId") Long ownerId) {
        return eventManager.getOwnerEvents(ownerId);
    }

    @GetMapping("/{memberId}")
    public List<EventDto> getMemberEvents(@PathVariable("memberId") Long memberId) {
        return eventManager.getMemberEvents(memberId);
    }
}
