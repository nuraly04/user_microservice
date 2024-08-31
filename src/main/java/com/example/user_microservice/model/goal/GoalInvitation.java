package com.example.user_microservice.model.goal;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "goal_invitation")
public class GoalInvitation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "inviter_id", nullable = false)
    private User inviter;

    @ManyToOne
    @JoinColumn(name = "invited_id", nullable = false)
    private User invited;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatusEnum status;
}
