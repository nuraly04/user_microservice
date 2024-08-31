package com.example.user_microservice.service.goal;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationFilterDto;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.GoalInvitation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.RequestStatusEnum;

import java.util.List;

public interface GoalInvitationService {

    GoalInvitation get(Long invitationId);

    GoalInvitation updateStatus(GoalInvitation goalInvitation, RequestStatusEnum statusEnum);

    List<GoalInvitation> getInvitations(GoalInvitationFilterDto filterDto);

    boolean existsByInviterAndInvitedAndGoalAndStatus(User inviter, User invited, Goal goal, RequestStatusEnum statusEnum);
}
