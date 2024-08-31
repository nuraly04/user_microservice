package com.example.user_microservice.controller.goal;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationCreateRequestDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationDto;
import com.example.user_microservice.dto.goal.invitation.GoalInvitationFilterDto;
import com.example.user_microservice.manager.goal.GoalInvitationManager;
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
@RequestMapping(Paths.GOAL_INVITATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalInvitationController {

    GoalInvitationManager goalInvitationManager;

    @PostMapping
    public GoalInvitationDto createInvitations(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId,
            @RequestBody @Valid GoalInvitationCreateRequestDto requestDto
    ) {
        return goalInvitationManager.createInvitation(userId, goalId, requestDto);
    }

    @PutMapping("/accept/{invitationId}")
    public GoalInvitationDto acceptInvitation(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId,
            @PathVariable("invitationId") Long invitationId
    ) {
        return goalInvitationManager.acceptInvitation(userId, goalId, invitationId);
    }

    @PutMapping("/reject/{invitationId}")
    public GoalInvitationDto rejectInvitation(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId,
            @PathVariable("invitationId") Long invitationId
    ) {
        return goalInvitationManager.rejectInvitation(userId, goalId, invitationId);
    }

    @GetMapping()
    public List<GoalInvitationDto> getInvitations(
            @PathVariable("userId") Long userId,
            @PathVariable("goalId") Long goalId,
            @RequestBody GoalInvitationFilterDto filterDto
    ) {
        return goalInvitationManager.getInvitations(filterDto);
    }
}
