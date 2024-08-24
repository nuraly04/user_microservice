package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.repository.skill.SkillOfferRepository;
import com.example.user_microservice.service.skill.SkillOfferService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillOfferServiceImpl implements SkillOfferService {

    SkillOfferRepository skillOfferRepository;


    @Override
    @Transactional(readOnly = true)
    public SkillOffer get(Long skillOfferId) {
        return skillOfferRepository.findById(skillOfferId).orElseThrow(
                () -> new DataNotFoundException(SkillOffer.class, skillOfferId, "id")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public SkillOffer findBySkillIdAndUserId(Long skillId, Long userId) {
        return skillOfferRepository.findBySkillIdAndUserId(skillId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countOfferedBySkillAndUser(Long skillId, Long userId) {
        return skillOfferRepository.countBySkillIdAndByUserId(skillId, userId);
    }
}
