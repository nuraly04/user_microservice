package com.example.user_microservice.repository.skill;

import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillOfferRepository extends JpaRepository<SkillOffer, Long> {

    @Query(nativeQuery = true, value = """
        SELECT count(*) FROM offer_skill os
        JOIN recommendation r on os.recommendation_id = r.id
        WHERE os.skill_id = :skillId
        AND r.receiver_id = :userId
        """)
    Long countBySkillIdAndByUserId(Long skillId, Long userId);

    @Query(nativeQuery = true, value = """
        SELECT FROM offer_skill os
        JOIN recommendation r on os.recommendation_id = r.id
        WHERE skill_id = :skillId
        AND r.receiver_id = :userId
        """)
    SkillOffer findBySkillIdAndUserId(Long skillId, Long userId);

    List<SkillOffer> findAllByIdIn(List<Long> skillOfferIds);

    List<SkillOffer> findByRecommendation(Recommendation recommendation);
}
