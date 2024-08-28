package com.example.user_microservice.service.reference.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.reference.QRefCommonReference;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.repository.reference.RefCommonReferenceRepository;
import com.example.user_microservice.service.reference.RefCommonReferenceService;
import com.example.user_microservice.utils.enums.RefCommonReferenceTypeEnum;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefCommonReferenceServiceImpl implements RefCommonReferenceService {

    RefCommonReferenceRepository referenceRepository;

    @Override
    @Transactional
    public RefCommonReference get(Long id) {
        return referenceRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(RefCommonReference.class, id, "id")
        );
    }


    @Override
    @Transactional(readOnly = true)
    public RefCommonReference findByCodeAndType(String code, RefCommonReferenceTypeEnum typeEnum) {
        BooleanBuilder predicate = new BooleanBuilder();
        QRefCommonReference reference = QRefCommonReference.refCommonReference;

        predicate.and(reference.code.eq(code))
                .and(reference.type.code.eq(typeEnum));
        return referenceRepository.findOne(predicate)
                .orElse(null);
    }
}
