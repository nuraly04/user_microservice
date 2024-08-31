package com.example.user_microservice.dto.goal.invitation;

import com.example.user_microservice.utils.enums.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalInvitationFilterDto {

    private Long invitationId;
    private Long inviterId;
    private Long invitedId;
    private Long goalId;
    private RequestStatusEnum statusEnum;
}
