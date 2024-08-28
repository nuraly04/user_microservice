package com.example.user_microservice.model.mentorship;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mentorship_request")
public class MentorshipRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private User mentee;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    private String content;

    @Enumerated(value = EnumType.STRING)
    private RequestStatusEnum status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
