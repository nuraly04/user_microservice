package com.example.user_microservice.model.skill;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false, length = 64)
    private String name;

    @ManyToMany
    @JoinTable(name = "m2m_user_skill",
                joinColumns = @JoinColumn(name = "skill_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToMany(mappedBy = "skills")
    private List<Goal> goals;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
