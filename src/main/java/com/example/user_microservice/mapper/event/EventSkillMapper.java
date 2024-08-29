package com.example.user_microservice.mapper.event;

import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.event.EventSkill;
import com.example.user_microservice.model.skill.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventSkillMapper {

    @Mapping(target = "id", ignore = true)
    EventSkill toEntity(Skill skill, Event event);
}
