package com.example.user_microservice.dto.skill;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillDto {

    @Min(0)
    private Long id;

    @NotBlank
    private String title;
}