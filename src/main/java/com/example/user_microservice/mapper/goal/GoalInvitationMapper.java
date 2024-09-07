package com.example.user_microservice.mapper.goal;

import com.example.user_microservice.dto.goal.invitation.GoalInvitationDto;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.GoalInvitation;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoalInvitationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    GoalInvitation toCreate(User inviter, User invited, Goal goal);

    @Mapping(target = "inviter.city", source = "inviter.city.name")
    @Mapping(target = "invited.city", source = "invited.city.name")
    GoalInvitationDto toDto(GoalInvitation invitation);
}
