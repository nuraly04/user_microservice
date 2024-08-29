package com.example.user_microservice.manager.event.impl;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.manager.event.EventManager;
import com.example.user_microservice.mapper.event.EventMapper;
import com.example.user_microservice.mapper.event.EventSkillMapper;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.event.EventService;
import com.example.user_microservice.service.event.EventSkillService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.validation.event.EventValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventManagerImpl implements EventManager {

    SkillService skillService;
    UserService userService;
    EventService eventService;
    EventSkillService eventSkillService;
    EventMapper eventMapper;
    EventSkillMapper eventSkillMapper;
    EventValidation eventValidation;

    @Override
    @Transactional
    public EventDto createEvent(Long ownerId, EventDto eventDto) {
        User owner = userService.get(ownerId);
        Event event = eventMapper.toEntity(eventDto);
        List<Skill> skills = skillService.findBySkillIds(eventDto.getSkills());
        eventValidation.checkSkills(skills, owner);
        eventService.createEvent(event);
        skills.forEach(skill -> eventSkillService.createEventSkill(eventSkillMapper.toEntity(skill, event)));
        return eventMapper.toDto(event);
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEvent(Long eventId) {
        Event event = eventService.get(eventId);
        return eventMapper.toDto(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getEvents(EventFilterDto filterDto) {
        List<Event> events = eventService.getEvents(filterDto);
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventService.get(eventId);
        eventService.deleteEvent(event);
    }
}
