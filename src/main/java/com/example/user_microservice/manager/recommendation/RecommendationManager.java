package com.example.user_microservice.manager.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;

import java.util.List;

public interface RecommendationManager {

    void giveRecommendation(RecommendationDto recommendation);

    RecommendationDto getRecommendation(Long recommendationId);

    List<RecommendationDto> getRecommendations(Long receiverId);

    List<RecommendationDto> getAllGivenRecommendation(Long authorId);

    void updateRecommendation(Long recommendationId, RecommendationDto recommendationDto);

    void deleteRecommendation(Long userId, Long recommendationId);
}
