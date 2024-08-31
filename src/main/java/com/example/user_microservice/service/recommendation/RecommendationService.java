package com.example.user_microservice.service.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;

import java.util.List;

public interface RecommendationService {

    Recommendation get(Long recommendationId);
    Recommendation create(Recommendation recommendation);

    List<Recommendation> findByReceiver(User receiver);

    List<Recommendation> findByAuthor(User author);
    void delete(User deletedBy, Recommendation recommendationId);
    Recommendation update(Recommendation recommendation, RecommendationDto recommendationDto);
    Recommendation findByAuthorAndReceiver(User author, User receiver);
}
