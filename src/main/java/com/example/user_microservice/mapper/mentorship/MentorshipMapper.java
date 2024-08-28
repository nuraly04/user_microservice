package com.example.user_microservice.mapper.mentorship;

import com.example.user_microservice.dto.mentorship.MentorshipRequestAddDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestDto;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MentorshipMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "mentee", target = "mentee")
    @Mapping(source = "mentor", target = "mentor")
    @Mapping(source = "requestDto.content", target = "content")
    MentorshipRequest toEntity(User mentee, User mentor, MentorshipRequestAddDto requestDto);

    @Mapping(target = "mentee", source = "mentee")
    MentorshipRequestDto toDto(UserDto mentee, MentorshipRequest request);
}
