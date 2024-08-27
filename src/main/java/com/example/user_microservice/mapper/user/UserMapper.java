package com.example.user_microservice.mapper.user;

import com.example.user_microservice.dto.mentorship.UserDto;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "patronymic", target = "patronymic")
    @Mapping(source = "user.skills.id", target = "skills.id")
    @Mapping(source = "user.skills.name", target = "skills.title")
    UserDto toMenteeDto(User user);
}
