package com.example.user_microservice.validation.recommendation;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.recommendation.Recommendation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class RecommendationValidationTest {

    @InjectMocks
    private RecommendationValidation recommendationValidation;

    private static final Long MIN_AVAILABLE_MONTH = 6L;

    @Test
    public void validateAvailableGiveRecommendationTestPositive() {

        Recommendation recommendation = new Recommendation();
        recommendation.setCreatedAt(LocalDateTime.now().minusMonths(MIN_AVAILABLE_MONTH + 1));
        recommendationValidation.validateAvailableGiveRecommendation(recommendation);
    }

    @Test
    public void validateAvailableGiveRecommendationTestNegative() {
        Recommendation recommendation = new Recommendation();
        recommendation.setCreatedAt(LocalDateTime.now().minusMonths(MIN_AVAILABLE_MONTH - 1));
        Assertions.assertThrows(DataValidationException.class, () -> recommendationValidation.validateAvailableGiveRecommendation(recommendation));
    }
}
