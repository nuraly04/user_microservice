package com.example.user_microservice.manager.recommendation.impl;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.dto.skill.SkillOfferDto;
import com.example.user_microservice.manager.recommendation.RecommendationManager;
import com.example.user_microservice.mapper.recommendation.RecommendationMapper;
import com.example.user_microservice.mapper.skill.SkillOfferMapper;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.recommendation.RecommendationService;
import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.service.user.UserSkillGuaranteeService;
import com.example.user_microservice.validation.recommendation.RecommendationValidation;
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
public class RecommendationManagerImpl implements RecommendationManager {

    UserService userService;
    SkillService skillService;
    SkillOfferMapper skillOfferMapper;
    SkillOfferService skillOfferService;
    RecommendationMapper recommendationMapper;
    UserSkillGuaranteeService guaranteeService;
    RecommendationService recommendationService;
    RecommendationValidation recommendationValidation;

    @Override
    @Transactional
    public void giveRecommendation(RecommendationDto recommendationDto) {
        User author = userService.get(recommendationDto.getAuthorId());
        User receiver = userService.get(recommendationDto.getReceiverId());
        recommendationValidation.validate;
        List<Skill> skills = skillService.findBySkillIds(recommendationDto.getSkillOffers().stream().map(SkillOfferDto::getSkillId).collect(Collectors.toList()));
        Recommendation recommendation = recommendationService.create(recommendationMapper.toEntity(recommendationDto, author, receiver));
        skills.forEach(skill -> skillOfferService.create(skillOfferMapper.toEntity(skill, recommendation)));
    }
}
