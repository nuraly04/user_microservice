package com.example.user_microservice.manager.event.impl;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.manager.event.EventManager;
import com.example.user_microservice.mapper.event.EventMapper;
import com.example.user_microservice.mapper.event.EventSkillMapper;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.event.EventSkill;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.event.EventService;
import com.example.user_microservice.service.event.EventSkillService;
import com.example.user_microservice.service.reference.RefCommonReferenceService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.validation.event.EventValidation;
import com.example.user_microservice.validation.time.TimeValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

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
    TimeValidation timeValidation;
    RefCommonReferenceService referenceService;

    @Override
    @Transactional
    public EventDto createEvent(Long ownerId, EventDto eventDto) {
        User owner = userService.get(ownerId);
        Event event = eventMapper.toEntity(eventDto);
        List<Skill> skills = skillService.findBySkillIds(eventDto.getSkills());
        eventValidation.checkSkills(skills, owner);
        eventService.saveOrUpdate(event);
        skills.forEach(skill -> eventSkillService.createEventSkill(eventSkillMapper.toEntity(skill, event)));
        return eventMapper.toDto(event);
    }

    @Override
    @Transactional
    public EventDto updateEvent(Long eventId, EventDto eventDto) {
        Event event = eventService.get(eventId);
        updateEvent(event, eventDto);
        eventService.saveOrUpdate(event);
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
    public void deleteEvent(Long userId, Long eventId) {
        User deletedBy = userService.get(userId);
        Event event = eventService.get(eventId);
        eventService.deleteEvent(deletedBy, event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getOwnerEvents(Long ownerId) {
        User owner = userService.get(ownerId);
        List<Event> events = eventService.findByOwner(owner);
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getMemberEvents(Long memberId) {
        User member = userService.get(memberId);
        List<Event> events = eventService.findByOwner(member);
        return new ArrayList<>();
    }

    private Event updateEvent(Event event, EventDto eventDto) {
        if (nonNull(eventDto.getTitle())) {
            event.setTitle(eventDto.getTitle());
        }
        if (nonNull(eventDto.getCityId())) {
            event.setCity(referenceService.get(eventDto.getCityId()));
        }
        if (nonNull(eventDto.getContent())) {
            event.setContent(eventDto.getContent());
        }
        if (nonNull(eventDto.getLocation())) {
            event.setLocation(eventDto.getLocation());
        }
        if (nonNull(eventDto.getEndDate())) {
            timeValidation.checkEndDate(eventDto.getEndDate());
            event.setEndDate(eventDto.getEndDate());
        }
        if (nonNull(eventDto.getStartDate())) {
            timeValidation.checkStartDate(eventDto.getStartDate());
            event.setStartDate(eventDto.getStartDate());
        }
        if (nonNull(eventDto.getMaxAttendees())) {
            event.setMaxAttendees(eventDto.getMaxAttendees());
        }
        if (nonNull(eventDto.getSkills()) && !eventDto.getSkills().isEmpty()) {
            List<Skill> skills1 = skillService.findBySkillIds(eventDto.getSkills());
            List<EventSkill> skills = skills1.stream()
                    .map(skill -> eventSkillMapper.toEntity(skill, event))
                    .toList();
            skills.forEach(eventSkillService::createEventSkill);
            event.setSkills(skills);
        }
        return event;
    }
}
