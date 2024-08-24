package com.example.user_microservice.manager.skill.impl;

import com.example.user_microservice.dto.skill.SkillCandidateDto;
import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.skill.SkillManager;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.service.user.UserSkillGuaranteeService;
import com.example.user_microservice.validation.skill.SkillValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillManagerImpl implements SkillManager {

    SkillService skillService;
    UserService userService;
    UserSkillGuaranteeService userSkillGuaranteeService;
    SkillValidation skillValidation;
    SkillOfferService skillOfferService;
    SkillMapper skillMapper;

    @Override
    @Transactional
    public SkillDto create(SkillDto skillDto) {
        Skill skill = skillService.create(skillMapper.toEntity(skillDto));
        return skillMapper.toDto(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDto> getUserSkills(Long userId) {
        List<Skill> skills = skillService.getUserSkills(userId);
        return skills.stream()
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillCandidateDto> getUserSkillsOffered(Long userId) {
        List<Skill> skills = skillService.getUserSkillsOffered(userId);
        return skills.stream()
                .map(skill -> skillMapper.toCandidateDto(skill, skillOfferService.countOfferedBySkillAndUser(skill.getId(), userId)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SkillDto acquireSkillFromOffers(Long skillId, Long userId) {
        Skill skill = skillService.get(skillId);
        skillValidation.validationSkill(skillId, userId);
        skillValidation.validationCountOfferedSkill(skillId, userId);
        SkillOffer skillOffer = skillOfferService.findBySkillIdAndUserId(skillId, userId);
        User user = userService.get(userId);
        User guarantor = userService.get(skillOffer.getRecommendation().getAuthor().getId());
        userSkillGuaranteeService.create(user, guarantor, skill);
        return skillMapper.toDto(skill);
    }
}
