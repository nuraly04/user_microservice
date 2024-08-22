package com.example.user_microservice.model.recommendation;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendation")
public class Recommendation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToMany(mappedBy = "recommendation")
    private List<SkillOffer> skillOffers;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
