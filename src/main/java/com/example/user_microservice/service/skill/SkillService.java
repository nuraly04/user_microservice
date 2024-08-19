package com.example.user_microservice.service.skill;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.model.skill.Skill;

public interface SkillService {

    Skill create(SkillDto dto);
}
