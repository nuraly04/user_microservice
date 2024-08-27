package com.example.user_microservice.service.recommendation.impl;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.recommendation.RecommendationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Mock
    private RecommendationRepository recommendationRepository;

    private RecommendationDto recommendationDto;
    private Recommendation recommendation;
    private Long recommendationId;
    private User receiver;
    private User author;

    @BeforeEach
    public void setUp() {
        recommendationDto = new RecommendationDto();
        recommendationDto.setId(1L);
        recommendation = new Recommendation();
        recommendation.setId(1L);
        recommendationId = 1L;
        receiver = new User();
        author = new User();
    }

    @Test
    public void getPositiveTest() {

        when(recommendationRepository.findById(recommendationId)).thenReturn(Optional.ofNullable(recommendation));

        recommendationService.get(recommendationId);

        verify(recommendationRepository, Mockito.times(1)).findById(recommendationId);
    }

    @Test
    public void getNegativeTest() {

        Assertions.assertThrows(DataNotFoundException.class, () -> recommendationService.get(recommendationId));
    }

    @Test
    public void createTest() {
        recommendationService.create(recommendation);

        verify(recommendationRepository, Mockito.times(1)).save(recommendation);
    }

    @Test
    public void findByReceiverTest() {
        recommendationService.findByReceiver(receiver);

        verify(recommendationRepository, Mockito.times(1)).findByReceiver(receiver);
    }

    @Test
    public void findByAuthorTest() {

        recommendationService.findByAuthor(author);

        verify(recommendationRepository, Mockito.times(1)).findByAuthor(author);
    }

    @Test
    public void deleteTest() {

        recommendationService.delete(recommendation);

        verify(recommendationRepository, Mockito.times(1)).delete(recommendation);
    }

    @Test
    public void updateTest() {
        recommendationService.update(recommendation, recommendationDto);

        verify(recommendationRepository, Mockito.times(1)).save(recommendation);
    }

    @Test
    public void findByAuthorAndReceiverTest() {

        recommendationService.findByAuthorAndReceiver(author, receiver);

        verify(recommendationRepository, Mockito.times(1)).findByAuthorAndReceiverOrderByIdDesc(author, receiver);
    }
}