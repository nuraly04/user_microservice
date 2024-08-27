package com.example.user_microservice.validation.recommendation;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.recommendation.Recommendation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationValidation {

    private final static Long MIN_MONTHS = 6L;

    public void validateAvailableGiveRecommendation(Recommendation recommendation) {
        if (Objects.nonNull(recommendation)) {
            LocalDateTime createdAt = recommendation.getCreatedAt();
            LocalDateTime available = LocalDateTime.now().minusMonths(MIN_MONTHS);

            if (createdAt.isAfter(available)) {
                throw new DataValidationException(String.format("Пользователь:%s уже дал рекомендацию этому пользователю:%s за последние 6 месяцев", recommendation.getAuthor(), recommendation.getReceiver()));
            }
        }
    }
}
