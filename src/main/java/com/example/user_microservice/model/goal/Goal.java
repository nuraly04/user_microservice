package com.example.user_microservice.model.goal;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.base.DeletedEntity;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.GoalStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Goal extends DeletedEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @OneToMany(mappedBy = "parent")
    private List<Goal> subGoals;

    @OneToMany(mappedBy = "goal")
    private List<GoalInvitation> goalInvitations;

    @ManyToMany
    @JoinTable(name = "m2m_goal_skill",
    joinColumns = @JoinColumn(name = "goal_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Goal parent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 64)
    private GoalStatusEnum status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
