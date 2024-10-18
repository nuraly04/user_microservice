package com.example.user_microservice.controller.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.manager.recommendation.RecommendationManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecommendationControllerTest {

    @InjectMocks
    private RecommendationController recommendationController;

    @Mock
    private RecommendationManager recommendationManager;

//    @Test
//    public void getRecommendationsTest() {
//
//        Long receiverId = 1L;
//
//        recommendationController.getRecommendations(receiverId);
//
//        verify(recommendationManager, Mockito.times(1)).getRecommendations(receiverId);
//
//    }
//
//    @Test
//    public void getRecommendation() {
//        Long recommendationId = 1L;
//
//        recommendationController.getRecommendation(recommendationId);
//
//        verify(recommendationManager, Mockito.times(1)).getRecommendation(recommendationId);
//    }
//
//    @Test
//    public void getAllGivenRecommendationTest() {
//
//        Long authorId = 1L;
//
//        recommendationController.getAllGivenRecommendation(authorId);
//
//        verify(recommendationManager, Mockito.times(1)).getAllGivenRecommendation(authorId);
//    }
//
//    @Test
//    public void giveRecommendationTest() {
//        RecommendationDto recommendationDto = RecommendationDto.builder()
//                .id(1L)
//                .authorId(23L)
//                .receiverId(67L)
//                .content("Java dev")
//                .build();
//
//        recommendationController.giveRecommendation(recommendationDto);
//
//        verify(recommendationManager, Mockito.times(1)).giveRecommendation(recommendationDto);
//    }
//
//    @Test
//    public void updateRecommendation() {
//
//        RecommendationDto recommendationDto = RecommendationDto.builder()
//                .id(1L)
//                .receiverId(87L)
//                .authorId(32L)
//                .content("Java Spring")
//                .build();
//
//        Long recommendationId = 1L;
//
//        recommendationController.updateRecommendation(recommendationId, recommendationDto);
//
//        verify(recommendationManager, Mockito.times(1)).updateRecommendation(recommendationId, recommendationDto);
//    }
//
//    @Test
//    public void deleteRecommendationTest() {
//        Long recommendationId = 1L;
//        Long userId = 1L;
//
//        recommendationController.deleteRecommendation(userId, recommendationId);
//
//        verify(recommendationManager, Mockito.times(1)).deleteRecommendation(userId, recommendationId);
//    }
}