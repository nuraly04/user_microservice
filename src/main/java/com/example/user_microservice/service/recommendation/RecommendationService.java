package com.example.user_microservice.service.recommendation;

import com.example.user_microservice.model.recommendation.Recommendation;

public interface RecommendationService {

    Recommendation create(Recommendation recommendation);
}
