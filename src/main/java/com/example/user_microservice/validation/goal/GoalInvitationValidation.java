package com.example.user_microservice.validation.goal;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.goal.GoalInvitationService;
import com.example.user_microservice.service.goal.GoalService;
import com.example.user_microservice.utils.enums.GoalStatusEnum;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalInvitationValidation {

    GoalInvitationService invitationService;
    GoalService goalService;
    static final int MAX_COUNT_GOAL = 3;

    public void checkExistsInvitation(User inviter, User invited, Goal goal, RequestStatusEnum statusEnum) {
        if (invitationService.existsByInviterAndInvitedAndGoalAndStatus(inviter, invited, goal, statusEnum)) {
            throw new DataValidationException("Уже существует запись");
        }
    }

    public void checkCountInvitation(User invited) {
        long countInvitedGoals = goalService.countGoalByMentorAndStatus(invited, GoalStatusEnum.ACTIVE);
        if (countInvitedGoals >= MAX_COUNT_GOAL) {
            throw new DataValidationException("Превышен лимит активных целей");
        }
    }
}
