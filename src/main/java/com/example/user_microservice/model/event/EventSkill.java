package com.example.user_microservice.model.event;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.skill.Skill;
import jakarta.persistence.*;

@Entity
@Table(name = "event_skill")
public class EventSkill extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
