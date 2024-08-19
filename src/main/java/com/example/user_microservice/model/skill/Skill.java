package com.example.user_microservice.model.skill;

import com.example.user_microservice.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false, length = 64)
    private String name;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
