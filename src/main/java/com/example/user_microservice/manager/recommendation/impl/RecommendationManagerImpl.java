package com.example.user_microservice.manager.recommendation.impl;

import com.example.user_microservice.dto.recommendation.CreateRecommendationRequestDto;
import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.dto.skill.SkillOfferDto;
import com.example.user_microservice.manager.recommendation.RecommendationManager;
import com.example.user_microservice.mapper.recommendation.RecommendationMapper;
import com.example.user_microservice.mapper.skill.SkillOfferMapper;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.mapper.user.UserSkillGuaranteeMapper;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationManagerImpl implements RecommendationManager {

    UserService userService;
    SkillService skillService;
    SkillOfferMapper skillOfferMapper;
    UserMapper userMapper;
    SkillOfferService skillOfferService;
    UserSkillGuaranteeMapper guaranteeMapper;
    RecommendationMapper recommendationMapper;
    UserSkillGuaranteeService guaranteeService;
    RecommendationService recommendationService;
    RecommendationValidation recommendationValidation;

    @Override
    @Transactional
    public RecommendationDto giveRecommendation(CreateRecommendationRequestDto recommendationDto) {

        User author = userService.get(recommendationDto.getAuthorId());
        User receiver = userService.get(recommendationDto.getReceiverId());

        recommendationValidation.validateAvailableGiveRecommendation(recommendationService.findByAuthorAndReceiver(author, receiver));

        List<Skill> skills = skillService.findBySkillIds(recommendationDto.getSkillOffers());
        List<UserSkillGuarantee> userSkills = guaranteeService.findBySkillsAndGuarantorAndUser(receiver, author, skills);
        List<Skill> skillsToGuarantee = skills.stream()
                .filter(skill -> userSkills.stream()
                        .noneMatch(userSkill -> userSkill.getSkill().equals(skill)))
                .toList();

        Recommendation recommendation = recommendationService.create(recommendationMapper.toEntity(recommendationDto, author, receiver));

        skills.forEach(skill -> skillOfferService.create(skillOfferMapper.toCreate(skill, recommendation)));
        skillsToGuarantee.forEach(skill -> guaranteeService.create(guaranteeMapper.create(receiver, author, skill)));
        List<SkillOfferDto> skillOffers = recommendation.getSkillOffers().stream()
                .map(skillOfferMapper::toDto)
                .toList();

        return recommendationMapper.toDto(recommendation, skillOffers, userMapper.toDto(author), userMapper.toDto(receiver));
    }

    @Override
    @Transactional(readOnly = true)
    public RecommendationDto getRecommendation(Long recommendationId) {
        Recommendation recommendation = recommendationService.get(recommendationId);
        List<SkillOfferDto> skillOffers = recommendation.getSkillOffers().stream()
                .map(skillOfferMapper::toDto)
                .toList();
        return recommendationMapper.toDto(recommendation, skillOffers, userMapper.toDto(recommendation.getAuthor()), userMapper.toDto(recommendation.getReceiver()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationDto> getRecommendations(Long receiverId) {
        User receiver = userService.get(receiverId);
        List<Recommendation> recommendations = recommendationService.findByReceiver(receiver);
        return getDtos(recommendations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationDto> getAllGivenRecommendation(Long authorId) {
        User author = userService.get(authorId);
        List<Recommendation> recommendations = recommendationService.findByAuthor(author);
        return getDtos(recommendations);
    }

    @Override
    @Transactional
    public void updateRecommendation(Long recommendationId, RecommendationDto recommendationDto) {
        Recommendation recommendation = recommendationService.get(recommendationId);
        recommendationService.update(recommendation, recommendationDto);
    }

    @Override
    @Transactional
    public void deleteRecommendation(Long userId, Long recommendationId) {
        User deletedBy = userService.get(userId);
        Recommendation recommendation = recommendationService.get(recommendationId);

        List<SkillOffer> skillOffers = skillOfferService.findByRecommendation(recommendation);
        List<Skill> skills = skillOffers.stream()
                .map(SkillOffer::getSkill)
                .toList();

        skillOffers.forEach(skillOfferService::delete);
        List<UserSkillGuarantee> skillGuarantees = guaranteeService.findBySkillsAndGuarantorAndUser(recommendation.getReceiver(), recommendation.getAuthor(), skills);
        skillGuarantees.forEach(guaranteeService::delete);

        recommendationService.delete(deletedBy, recommendation);
    }

    private List<RecommendationDto> getDtos(List<Recommendation> recommendations) {
        return recommendations.stream()
                .map(recommendation -> recommendationMapper.toDto(
                        recommendation,
                        recommendation.getSkillOffers().stream().map(skillOfferMapper::toDto).toList(),
                        userMapper.toDto(recommendation.getAuthor()),
                        userMapper.toDto(recommendation.getReceiver())
                ))
                .toList();
    }
}
