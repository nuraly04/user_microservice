package com.example.user_microservice.mapper.goal;

import com.example.user_microservice.dto.goal.GoalCreateRequestDto;
import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoalMapper {

    @Mapping(target = "mentor", source = "mentor")
    Goal toCreate(GoalCreateRequestDto requestDto, User mentor);

    @Mapping(target = "mentor.city", source = "mentor.city.name")
    GoalDto toDto(Goal goal);
}
