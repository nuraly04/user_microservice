package com.example.user_microservice.service.skill;

import com.example.user_microservice.model.skill.SkillOffer;

import java.util.List;

public interface SkillOfferService {

    SkillOffer create(SkillOffer skillOffer);

    SkillOffer get(Long skillOfferId);

    SkillOffer findBySkillIdAndUserId(Long skillId, Long userId);

    Long countOfferedBySkillAndUser(Long skillId, Long userId);

    List<SkillOffer> findBySkillOfferIds(List<Long> skillOfferIds);
}
