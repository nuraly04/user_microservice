package com.example.user_microservice.dto.goal.invitation;

import com.example.user_microservice.dto.goal.GoalDto;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalInvitationDto {

    private UserDto inviter;
    private UserDto invited;
    private GoalDto goalDto;
    private RequestStatusEnum status;
}
