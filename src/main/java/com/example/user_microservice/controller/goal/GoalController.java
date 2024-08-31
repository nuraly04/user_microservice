package com.example.user_microservice.controller.goal;

import com.example.user_microservice.dto.goal.GoalCreateRequestDto;
import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.dto.goal.GoalFilterDto;
import com.example.user_microservice.dto.goal.GoalUpdateRequestDto;
import com.example.user_microservice.manager.goal.GoalManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.GOAL)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalController {

    GoalManager goalManager;

    @PostMapping()
    public GoalDto createGoal(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid GoalCreateRequestDto requestDto) {
        return goalManager.createGoal(userId, requestDto);
    }

    @PutMapping("/{goalId}")
    public GoalDto updateGoal(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId,
            @RequestBody GoalUpdateRequestDto requestDto
    ) {
        return goalManager.updateGoal(userId, goalId, requestDto);
    }

    @PutMapping("/delete/{goalId}")
    public void deleteGoal(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId
    ) {
        goalManager.deleteGoal(userId, goalId);
    }

    @GetMapping("/{goalId}/subGoals")
    public List<GoalDto> getSubGoals(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId
    ) {
        return goalManager.getSubGoals(userId, goalId);
    }

    @GetMapping("/list")
    public List<GoalDto> getGoals(
            @PathVariable("userId") Long userId,
            @RequestBody GoalFilterDto filterDto
            ) {
        return goalManager.getGoals(userId, filterDto);
    }
}
