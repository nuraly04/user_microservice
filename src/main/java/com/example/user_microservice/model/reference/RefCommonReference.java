package com.example.user_microservice.model.reference;

import com.example.user_microservice.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
@Table(name = "ref_common_reference")
public class RefCommonReference extends BaseEntity {

    @OneToOne
    private RefCommonReferenceType type;

    @NotBlank
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @NotBlank
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private RefCommonReference parent;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}