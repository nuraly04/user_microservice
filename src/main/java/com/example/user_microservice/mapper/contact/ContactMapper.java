package com.example.user_microservice.mapper.contact;

import com.example.user_microservice.dto.user.ContactDto;
import com.example.user_microservice.model.contact.Contact;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "author", target = "user")
    Contact toEntity(ContactDto dto, User author);
}
