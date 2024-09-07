package com.example.user_microservice.repository.skill;

import com.example.user_microservice.model.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>, QuerydslPredicateExecutor<Skill> {

    @Query(nativeQuery = true, value = """
            SELECT so.* FROM skill so
            JOIN m2m_user_skill us ON so.id = us.skill_id
            WHERE us.user_id = :userId
            """)
    List<Skill> findAllByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = """
            SELECT s.* FROM offer_skill of
            JOIN recommendation r on of.recommendation_id = r.id
            JOIN skill s on of.skill_id = s.id
            WHERE r.receiver_id = :userId
            """)
    List<Skill> findAllOfferedByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = """
            SELECT FROM skill s 
            JOIN m2m_user_skill us ON s.id = us.skill_id
            WHERE us.skill_id = :skillId
            AND us.user_id = :userId
            """)
    Skill findBySkillIdAndByUserId(Long skillId, Long userId);

    List<Skill> findSkillsByIdIn(List<Long> skillIds);
}