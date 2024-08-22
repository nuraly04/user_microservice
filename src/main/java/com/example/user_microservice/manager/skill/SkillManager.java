package com.example.user_microservice.manager.skill;

import com.example.user_microservice.dto.skill.SkillCandidateDto;
import com.example.user_microservice.dto.skill.SkillDto;

import java.util.List;

public interface SkillManager {

    SkillDto create(SkillDto dto);

    List<SkillDto> getUserSkills(Long userId);

    List<SkillCandidateDto> getUserSkillsOffered(Long userId);

    SkillDto acquireSkillFRomOffers(Long skillId, Long userId);
}