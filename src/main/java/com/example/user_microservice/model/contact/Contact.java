package com.example.user_microservice.model.contact;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.ContactTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String contact;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ContactTypeEnum type;
}
