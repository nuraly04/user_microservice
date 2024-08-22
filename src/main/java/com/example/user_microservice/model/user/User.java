package com.example.user_microservice.model.user;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.model.skill.Skill;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "surname", nullable = false, length = 64)
    private String surname;

    @Column(name = "patronymic", nullable = false, length = 64)
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "city")
    private RefCommonReference city;

    @ManyToMany(mappedBy = "users")
    private List<Skill> skills;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 128)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 64)
    private String phone;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
