package com.example.user_microservice.model.user;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.skill.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_skill_guarantee")
public class UserSkillGuarantee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "guarantor_id", nullable = false)
    private User guarantor;
}
