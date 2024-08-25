package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.skill.SkillRepository;
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
public class SkillServiceImpl implements SkillService {

    SkillRepository skillRepository;

    @Override
    @Transactional(readOnly = true)
    public Skill get(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new DataNotFoundException(Skill.class, skillId, "id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Skill findBySkillIdAndByUserId(Long skillId, Long userId) {
        return skillRepository.findBySkillIdAndByUserId(skillId, userId);
    }

    @Override
    @Transactional
    public List<Skill> findBySkillIds(List<Long> skillIds) {
        return skillRepository.findSkillsByIdIn(skillIds);
    }

    @Override
    @Transactional
    public Skill create(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> getUserSkills(Long userId) {
        return skillRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> getUserSkillsOffered(Long userId) {
        return skillRepository.findAllOfferedByUserId(userId);
    }
}