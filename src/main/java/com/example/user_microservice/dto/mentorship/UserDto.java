package com.example.user_microservice.dto.mentorship;

import com.example.user_microservice.dto.skill.SkillDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private List<SkillDto> skills;
}
