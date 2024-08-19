package com.example.user_microservice.manager.skill.impl;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.skill.SkillManager;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.service.skill.SkillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillManagerImpl implements SkillManager {

    SkillService skillService;
    SkillMapper skillMapper;

    @Override
    @Transactional
    public SkillDto create(SkillDto skillDto) {
        Skill skill = skillService.create(skillDto);
        return skillMapper.toDto(skill);
    }

    @Override
    @Transactional
    public List<SkillDto> getUserSkills(Long userId) {
        return null;
    }
}
