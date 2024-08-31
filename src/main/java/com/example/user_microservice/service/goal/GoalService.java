package com.example.user_microservice.service.goal;

import com.example.user_microservice.dto.goal.GoalFilterDto;
import com.example.user_microservice.dto.goal.GoalUpdateRequestDto;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.GoalStatusEnum;

import java.util.List;

public interface GoalService {

    Goal get(Long goalId);

    Goal saveOrUpdate(Goal goal);

    Goal updateGoal(Goal goal, GoalUpdateRequestDto requestDto);

    List<Goal> findGoalByMentorAndStatus(User mentor, GoalStatusEnum statusEnum);

    List<Goal> getGoals(GoalFilterDto filterDto);

    List<Goal> findByParentGoal(Goal parentGoal);

    long countGoalByMentorAndStatus(User mentor, GoalStatusEnum statusEnum);

    void deleteGoal(User deletedBy, Goal goal);

    void updateSkills(List<Skill> skills, Goal goal);
}
