package com.example.user_microservice.service.skill.impl;

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
    public Long countOfferedBySkillAndUser(Long skillId, Long userId) {
        return skillOfferRepository.countBySkillIdAndByUserId(skillId, userId);
    }
}
