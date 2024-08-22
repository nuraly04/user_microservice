package com.example.user_microservice.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillCandidateDto {

    private SkillDto skillDto;
    private Long offeredAmount;
}
