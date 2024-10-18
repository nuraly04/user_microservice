package com.example.user_microservice.service.event.impl;

import com.example.user_microservice.dto.event.EventFilterDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.event.EventRepository;
import com.example.user_microservice.service.event.EventService;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.user_microservice.model.event.QEvent.event;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    @Override
    @Transactional
    public Event get(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new DataNotFoundException(Event.class, eventId, "id")
        );
    }

    @Override
    @Transactional
    public Event saveOrUpdate(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public List<Event> findByOwner(User owner) {
        return eventRepository.findAllByOwner(owner);
    }

    @Override
    @Transactional
    public List<Event> findByMember(User member) {
        return eventRepository.findAllByUsers(member);
    }

    @Override
    @Transactional
    public List<Event> getEvents(EventFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        search(predicate, filterDto);
        return (List<Event>) eventRepository.findAll(predicate);
    }

    @Override
    @Transactional
    public void deleteEvent(User deletedBy, Event event) {
        event.setDeletedAt(LocalDateTime.now());
        event.setDeletedBy(deletedBy);
        eventRepository.save(event);
    }

    private void search(BooleanBuilder predicate, EventFilterDto filterDto) {
        if (nonNull(filterDto.getOwnerId())) {
            predicate.and(event.owner.id.eq(filterDto.getOwnerId()));
        }
        if (nonNull(filterDto.getTitle())) {
            predicate.and(event.title.containsIgnoreCase(filterDto.getTitle()));
        }
        if (nonNull(filterDto.getContent())) {
            predicate.and(event.content.containsIgnoreCase(filterDto.getContent()));
        }
        if (nonNull(filterDto.getCityId())) {
            predicate.and(event.city.id.eq(filterDto.getCityId()));
        }
        if (nonNull(filterDto.getMinAttendees())) {
            if (nonNull(filterDto.getMaxAttendees())) {
                predicate.and(event.maxAttendees.between(filterDto.getMinAttendees(), filterDto.getMaxAttendees()));
            }
        }
        if (nonNull(filterDto.getStartDate())) {
            if (nonNull(filterDto.getEndDate())) {
                predicate.and(event.startDate.between(filterDto.getStartDate(), filterDto.getEndDate()));
            } else predicate.and(event.startDate.between(filterDto.getStartDate(), LocalDateTime.now()));
        }
        if (nonNull(filterDto.getAfterCreatedAt())) {
            if (nonNull(filterDto.getBeforeCreatedAt())) {
                predicate.and(event.createdAt.between(filterDto.getAfterCreatedAt(), filterDto.getBeforeCreatedAt()));
            } else predicate.and(event.createdAt.between(filterDto.getAfterCreatedAt(), LocalDateTime.now()));
        }
        if (nonNull(filterDto.getSkillIds()) && !filterDto.getSkillIds().isEmpty()) {
            predicate.and(event.skills.any().skill.id.in(filterDto.getSkillIds()));
        }
    }
}
