package com.example.user_microservice.dto.goal.invitation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalInvitationCreateRequestDto {

    @NotNull
    private Long invitedUserId;

    @NotNull
    private Long goalId;
}
