package com.example.user_microservice.mapper.goal;

import com.example.user_microservice.dto.goal.GoalCreateRequestDto;
import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.model.goal.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoalMapper {

    Goal toEntity(GoalDto dto);

    Goal toCreate(GoalCreateRequestDto requestDto);

    GoalDto toDto(Goal goal);
}
