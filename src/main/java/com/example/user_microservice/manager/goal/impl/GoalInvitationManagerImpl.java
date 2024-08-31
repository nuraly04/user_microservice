package com.example.user_microservice.manager.goal.impl;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationCreateRequestDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationFilterDto;
import com.example.user_microservice.manager.goal.GoalInvitationManager;
import com.example.user_microservice.mapper.goal.GoalInvitationMapper;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.GoalInvitation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.goal.GoalInvitationService;
import com.example.user_microservice.service.goal.GoalService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import com.example.user_microservice.validation.goal.GoalInvitationValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalInvitationManagerImpl implements GoalInvitationManager {

    UserService userService;
    GoalService goalService;
    GoalInvitationService invitationService;
    GoalInvitationMapper invitationMapper;
    GoalInvitationValidation invitationValidation;

    @Override
    @Transactional
    public GoalInvitationDto createInvitation(Long userId, Long goalId, GoalInvitationCreateRequestDto requestDto) {
        User inviter = userService.get(userId);
        User invited = userService.get(requestDto.getInvitedUserId());
        Goal goal = goalService.get(goalId);
        invitationValidation.checkExistsInvitation(inviter, invited, goal, RequestStatusEnum.PENDING);
        GoalInvitation invitation = invitationService.updateStatus(invitationMapper.toCreate(inviter, invited, goal), RequestStatusEnum.PENDING);
        return invitationMapper.toDto(invitation);
    }

    @Override
    @Transactional
    public GoalInvitationDto acceptInvitation(Long userId, Long goalId, Long invitationId) {
        User user = userService.get(userId);
        GoalInvitation invitation = invitationService.get(invitationId);
        invitationValidation.checkExistsInvitation(invitation.getInviter(), user, invitation.getGoal(), RequestStatusEnum.ACCEPTED);
        invitationValidation.checkCountInvitation(user);
        invitationService.updateStatus(invitation, RequestStatusEnum.ACCEPTED);
        return invitationMapper.toDto(invitation);
    }

    @Override
    @Transactional
    public GoalInvitationDto rejectInvitation(Long userId, Long goalId, Long invitationId) {
        User user = userService.get(userId);
        GoalInvitation invitation = invitationService.get(invitationId);
        invitationValidation.checkExistsInvitation(invitation.getInviter(), user, invitation.getGoal(), RequestStatusEnum.REJECTED);
        invitationService.updateStatus(invitation, RequestStatusEnum.REJECTED);
        return invitationMapper.toDto(invitation);
    }

    @Override
    @Transactional
    public List<GoalInvitationDto> getInvitations(GoalInvitationFilterDto filterDto) {
        List<GoalInvitation> invitations = invitationService.getInvitations(filterDto);
        return invitations.stream()
                .map(invitationMapper::toDto)
                .toList();
    }
}
