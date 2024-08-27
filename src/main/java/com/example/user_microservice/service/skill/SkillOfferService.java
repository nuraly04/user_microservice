package com.example.user_microservice.service.skill;

import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.SkillOffer;

import java.util.List;

public interface SkillOfferService {

    SkillOffer create(SkillOffer skillOffer);

    SkillOffer get(Long skillOfferId);

    void delete(SkillOffer skillOffer);

    SkillOffer findBySkillIdAndUserId(Long skillId, Long userId);

    Long countOfferedBySkillAndUser(Long skillId, Long userId);

    List<SkillOffer> findBySkillOfferIds(List<Long> skillOfferIds);

    List<SkillOffer> findByRecommendation(Recommendation recommendation);
}
