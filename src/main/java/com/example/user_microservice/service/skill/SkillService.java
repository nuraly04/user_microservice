package com.example.user_microservice.service.skill;

import com.example.user_microservice.model.skill.Skill;

import java.util.List;

public interface SkillService {

    Skill get(Long skillId);

    Skill create(Skill skill);

    List<Skill> getUserSkills(Long userId);

    List<Skill> getUserSkillsOffered(Long userId);

    Skill findBySkillIdAndByUserId(Long skillId, Long userId);
}
