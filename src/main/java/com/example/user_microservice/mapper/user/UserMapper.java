package com.example.user_microservice.mapper.user;

import com.example.user_microservice.dto.user.UserCreateRequest;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "patronymic", target = "patronymic")
    UserDto toDto(User user);

    User toEntity(UserCreateRequest userDto);
}
