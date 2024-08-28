package com.example.user_microservice.repository.reference;

import com.example.user_microservice.model.reference.RefCommonReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RefCommonReferenceRepository extends JpaRepository<RefCommonReference, Long>, QuerydslPredicateExecutor<RefCommonReference> {
}
