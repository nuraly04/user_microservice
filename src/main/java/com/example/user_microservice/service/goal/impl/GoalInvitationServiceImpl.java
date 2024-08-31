package com.example.user_microservice.service.goal.impl;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationFilterDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.GoalInvitation;
import com.example.user_microservice.model.goal.QGoalInvitation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.goal.GoalInvitationRepository;
import com.example.user_microservice.service.goal.GoalInvitationService;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.user_microservice.model.goal.QGoalInvitation.goalInvitation;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalInvitationServiceImpl implements GoalInvitationService {

    GoalInvitationRepository invitationRepository;

    @Override
    @Transactional
    public GoalInvitation get(Long invitationId) {
        return invitationRepository.findById(invitationId).orElseThrow(
                () -> new DataNotFoundException(GoalInvitation.class, invitationId, "id")
        );
    }

    @Override
    @Transactional
    public GoalInvitation updateStatus(GoalInvitation invitation, RequestStatusEnum statusEnum) {
        invitation.setStatus(statusEnum);
        return invitation;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByInviterAndInvitedAndGoalAndStatus(
            User inviter,
            User invited,
            Goal goal,
            RequestStatusEnum statusEnum
    ) {
        return invitationRepository.existsByInviterAndInvitedAndGoalAndStatus(inviter, invited, goal, statusEnum);
    }

    @Override
    @Transactional
    public List<GoalInvitation> getInvitations(GoalInvitationFilterDto filterDto) {
        BooleanBuilder predicate  = new BooleanBuilder();
        search(predicate, filterDto);
        List<GoalInvitation> invitations = new ArrayList<>();
        invitationRepository.findAll(predicate).forEach(invitations::add);
        return invitations;
    }

    private void search(BooleanBuilder predicate, GoalInvitationFilterDto filterDto) {
        if (nonNull(filterDto.getInvitationId())) {
            predicate.and(goalInvitation.id.eq(filterDto.getInvitationId()));
        }
        if (nonNull(filterDto.getInviterId())) {
            predicate.and(goalInvitation.inviter.id.eq(filterDto.getInviterId()));
        }
        if (nonNull(filterDto.getInvitedId())) {
            predicate.and(goalInvitation.invited.id.eq(filterDto.getInvitedId()));
        }
        if (nonNull(filterDto.getGoalId())) {
            predicate.and(goalInvitation.goal.id.eq(filterDto.getGoalId()));
        }
        if (nonNull(filterDto.getStatusEnum())) {
            predicate.and(goalInvitation.status.eq(filterDto.getStatusEnum()));
        }
    }
}
