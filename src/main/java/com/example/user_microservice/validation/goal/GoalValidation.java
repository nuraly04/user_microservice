package com.example.user_microservice.validation.goal;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.goal.GoalService;
import com.example.user_microservice.utils.enums.GoalStatusEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalValidation {

    GoalService goalService;

    static final Integer MAX_AVAILABLE_GOAL = 3;

    public void checkCountActiveGoalByMentor(User mentor) {
        long countGoals = goalService.countGoalByMentorAndStatus(mentor, GoalStatusEnum.ACTIVE);
        if (countGoals >= MAX_AVAILABLE_GOAL) {
            throw new DataValidationException(String.format("Превышен лимит целей у пользователя %s", mentor));
        }
    }

    public void checkAvailableSkills(long skills, long expectedCountSkills) {
        if (skills != expectedCountSkills) {
            throw new DataValidationException("Скиллы не найдены");
        }
    }

}
