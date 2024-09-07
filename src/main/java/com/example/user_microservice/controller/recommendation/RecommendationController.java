package com.example.user_microservice.controller.recommendation;

import com.example.user_microservice.dto.recommendation.CreateRecommendationRequestDto;
import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.manager.recommendation.RecommendationManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.RECOMMENDATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationController {

    RecommendationManager recommendationManager;

    @GetMapping("/received/{receiverId}")
    public List<RecommendationDto> getRecommendations(@PathVariable("receiverId") Long receiverId) {
        return recommendationManager.getRecommendations(receiverId);
    }

    @GetMapping("/{recommendationId}")
    public RecommendationDto getRecommendation(@PathVariable("recommendationId") Long recommendationId) {
        return recommendationManager.getRecommendation(recommendationId);
    }

    @GetMapping("/given/{authorId}")
    public List<RecommendationDto> getAllGivenRecommendation(@PathVariable("authorId") Long authorId) {
        return recommendationManager.getAllGivenRecommendation(authorId);
    }

    @PostMapping()
    public RecommendationDto giveRecommendation(@RequestBody @Valid CreateRecommendationRequestDto recommendation) {
        return recommendationManager.giveRecommendation(recommendation);
    }

    @PutMapping("/{recommendationId}")
    public void updateRecommendation(
            @PathVariable("recommendationId") Long recommendationId,
            @RequestBody @Valid RecommendationDto recommendationDto
    ) {
        recommendationManager.updateRecommendation(recommendationId, recommendationDto);
    }

    @PutMapping("/delete/{recommendationId}")
    public void deleteRecommendation(
            @RequestParam Long userId,
            @PathVariable("recommendationId") Long recommendationId
    ) {
        recommendationManager.deleteRecommendation(userId, recommendationId);
    }
}
