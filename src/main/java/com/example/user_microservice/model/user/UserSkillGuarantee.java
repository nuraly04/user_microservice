package com.example.user_microservice.model.user;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.skill.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class UserSkillGuarantee extends BaseEntity {

    @ManyToOne
    private Skill skill;

    @ManyToOne
    private User user;
}
