package com.example.user_microservice.manager.goal;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationCreateRequestDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationFilterDto;

import java.util.List;

public interface GoalInvitationManager {

    GoalInvitationDto createInvitation(Long userId, Long goalId, GoalInvitationCreateRequestDto requestDto);

    GoalInvitationDto acceptInvitation(Long userId, Long goalId, Long invitationId);

    GoalInvitationDto rejectInvitation(Long userId, Long goalId, Long invitationId);

    List<GoalInvitationDto> getInvitations(GoalInvitationFilterDto filterDto);
}
