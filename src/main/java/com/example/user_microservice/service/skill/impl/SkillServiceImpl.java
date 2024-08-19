package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.skill.SkillRepository;
import com.example.user_microservice.service.skill.SkillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillServiceImpl implements SkillService {

    SkillRepository skillRepository;
    SkillMapper skillMapper;

    @Override
    @Transactional
    public Skill create(SkillDto dto) {
        return skillRepository.save(skillMapper.toEntity(dto));
    }
}
