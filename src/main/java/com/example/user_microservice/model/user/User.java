package com.example.user_microservice.model.user;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.reference.RefCommonReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @OneToOne
    private RefCommonReference city;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 128)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 64)
    private String phone;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
