package com.example.user_microservice.model.skill;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.recommendation.Recommendation;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill_offer")
public class SkillOffer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    private Recommendation recommendation;
}
