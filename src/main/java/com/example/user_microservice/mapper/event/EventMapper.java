package com.example.user_microservice.mapper.event;

import com.example.user_microservice.dto.event.EventDto;
import com.example.user_microservice.model.event.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(target = "skills", ignore = true)
    Event toEntity(EventDto event);

    @Mapping(target = "skills", ignore = true)
    EventDto toDto(Event event);
}
