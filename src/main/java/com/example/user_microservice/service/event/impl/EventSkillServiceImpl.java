package com.example.user_microservice.service.event.impl;

import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.event.EventSkill;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.event.EventSkillRepository;
import com.example.user_microservice.service.event.EventSkillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventSkillServiceImpl implements EventSkillService {

    EventSkillRepository eventSkillRepository;

    @Override
    @Transactional
    public void createEventSkill(EventSkill eventSkill) {
        eventSkillRepository.save(eventSkill);
    }
}
