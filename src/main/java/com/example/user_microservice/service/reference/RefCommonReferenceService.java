package com.example.user_microservice.service.reference;

import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.utils.enums.RefCommonReferenceTypeEnum;

public interface RefCommonReferenceService {

    RefCommonReference get(Long referenceId);

    RefCommonReference findByCodeAndType(String code, RefCommonReferenceTypeEnum typeEnum);
}
