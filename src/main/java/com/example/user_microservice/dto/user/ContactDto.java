package com.example.user_microservice.dto.user;

import com.example.user_microservice.utils.enums.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private ContactTypeEnum type;
    private String contact;
}
