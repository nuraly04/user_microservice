package com.example.user_microservice.manager.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;

public interface RecommendationManager {

    void giveRecommendation(RecommendationDto recommendation);
}
