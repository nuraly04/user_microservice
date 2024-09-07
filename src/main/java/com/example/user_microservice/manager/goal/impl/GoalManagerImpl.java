package com.example.user_microservice.manager.goal.impl;

import com.example.user_microservice.dto.goal.GoalCreateRequestDto;
import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.dto.goal.GoalFilterDto;
import com.example.user_microservice.dto.goal.GoalUpdateRequestDto;
import com.example.user_microservice.manager.goal.GoalManager;
import com.example.user_microservice.mapper.goal.GoalMapper;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.goal.GoalService;
import com.example.user_microservice.service.reference.RefCommonReferenceService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.validation.goal.GoalValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalManagerImpl implements GoalManager {

    SkillService skillService;
    GoalService goalService;
    RefCommonReferenceService referenceService;
    GoalMapper goalMapper;
    UserService userService;
    GoalValidation goalValidation;

    @Override
    @Transactional
    public GoalDto createGoal(Long mentorId, GoalCreateRequestDto requestDto) {
        User mentor = userService.get(mentorId);
        List<Skill> skills = skillService.findBySkillIds(requestDto.getSkillIds());
        goalValidation.checkCountActiveGoalByMentor(mentor);
        goalValidation.checkAvailableSkills(skills.size(), requestDto.getSkillIds().size());
        Goal goal = goalMapper.toCreate(requestDto, mentor);
        goal.setSkills(skills);
        goalService.saveOrUpdate(goal);
        return goalMapper.toDto(goal);
    }

    @Override
    @Transactional
    public GoalDto updateGoal(Long mentorId, Long goalId, GoalUpdateRequestDto requestDto) {
        userService.get(mentorId);
        Goal goal = goalService.get(goalId);
        List<Skill> skills = skillService.findBySkillIds(requestDto.getSkillIds());
        goalValidation.checkAvailableSkills(skills.size(), requestDto.getSkillIds().size());
        goalService.updateSkills(skills, goal);
        goalService.updateGoal(goal, requestDto);
        return goalMapper.toDto(goal);
    }

    @Override
    @Transactional
    public void deleteGoal(Long userId, Long goalId) {
        User deletedBy = userService.get(userId);
        Goal goal = goalService.get(goalId);
        goalService.deleteGoal(deletedBy, goal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getSubGoals(Long userId, Long goalId) {
        Goal parentGoal = goalService.get(goalId);
        List<Goal> goals = goalService.findByParentGoal(parentGoal);
        return goals.stream()
                .map(goalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getGoals(Long userId, GoalFilterDto filterDto) {
        List<Goal> goals = goalService.getGoals(filterDto);
        return goals.stream()
                .map(goalMapper::toDto)
                .toList();
    }

}
