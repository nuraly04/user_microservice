package com.example.user_microservice.controller.event;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.event.EventManager;
import com.example.user_microservice.repository.event.EventRepository;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.EVENT)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {

    EventManager eventManager;
    private final EventRepository eventRepository;

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
}
