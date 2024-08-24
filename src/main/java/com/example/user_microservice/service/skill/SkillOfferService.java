package com.example.user_microservice.service.skill;

import com.example.user_microservice.model.skill.SkillOffer;

public interface SkillOfferService {

    SkillOffer get(Long skillOfferId);

    SkillOffer findBySkillIdAndUserId(Long skillId, Long userId);

    Long countOfferedBySkillAndUser(Long skillId, Long userId);
}
