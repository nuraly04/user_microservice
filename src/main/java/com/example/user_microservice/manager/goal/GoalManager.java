package com.example.user_microservice.manager.goal;

import com.example.user_microservice.dto.goal.GoalCreateRequestDto;
import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.dto.goal.GoalFilterDto;
import com.example.user_microservice.dto.goal.GoalUpdateRequestDto;

import java.util.List;

public interface GoalManager {

    GoalDto createGoal(Long mentorId, GoalCreateRequestDto requestDto);

    GoalDto updateGoal(Long mentorId, Long goalId, GoalUpdateRequestDto requestDto);

    void deleteGoal(Long userId, Long goalId);

    List<GoalDto> getSubGoals(Long userId, Long goalId);

    List<GoalDto> getGoals(Long userId, GoalFilterDto filterDto);
}
