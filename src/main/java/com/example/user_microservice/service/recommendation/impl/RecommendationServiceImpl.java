package com.example.user_microservice.service.recommendation.impl;

import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.repository.recommendation.RecommendationRepository;
import com.example.user_microservice.service.recommendation.RecommendationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationServiceImpl implements RecommendationService {

    RecommendationRepository recommendationRepository;

    @Override
    @Transactional
    public Recommendation create(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}
