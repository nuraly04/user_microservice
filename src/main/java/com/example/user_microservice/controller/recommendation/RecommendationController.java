package com.example.user_microservice.controller.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.manager.recommendation.RecommendationManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.RECOMMENDATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationController {

    RecommendationManager recommendationManager;

    @PostMapping()
    public void giveRecommendation(@RequestBody @Valid RecommendationDto recommendation) {
        recommendationManager.giveRecommendation(recommendation);
    }
}
