package com.example.user_microservice.model.reference;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.utils.enums.RefCommonReferenceTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ref_common_reference_type")
public class RefCommonReferenceType extends BaseEntity {

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    @Column(name = "code", unique = true, nullable = false, length = 64)
    private RefCommonReferenceTypeEnum code;

    @NotBlank
    @Column(name = "name", nullable = false, length = 64)
    private String name;
}