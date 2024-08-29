package com.example.user_microservice.dto.user;

import com.example.user_microservice.utils.enums.ContactTypeEnum;
import com.example.user_microservice.utils.enums.RefCommonReferenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterDto {

    private Long id;
    private List<Long> followerIds;
    private String name;
    private String about;
    private String email;
    private ContactTypeEnum contact;
    private RefCommonReferenceTypeEnum city;
    private RefCommonReferenceTypeEnum country;
    private String phone;
    private String skill;
}
