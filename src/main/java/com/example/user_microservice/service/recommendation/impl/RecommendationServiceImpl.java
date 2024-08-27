package com.example.user_microservice.service.recommendation.impl;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.recommendation.RecommendationRepository;
import com.example.user_microservice.service.recommendation.RecommendationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationServiceImpl implements RecommendationService {

    RecommendationRepository recommendationRepository;

    @Override
    @Transactional
    public Recommendation get(Long recommendationId) {
        return recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new DataNotFoundException(Recommendation.class, recommendationId, "id"));
    }

    @Override
    @Transactional
    public Recommendation create(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    @Transactional
    public List<Recommendation> findByReceiver(User receiver) {
        return recommendationRepository.findByReceiver(receiver);
    }

    @Override
    @Transactional
    public List<Recommendation> findByAuthor(User author) {
        return recommendationRepository.findByAuthor(author);
    }
    @Override
    @Transactional
    public void delete(Recommendation recommendation) {
        recommendationRepository.delete(recommendation);
    }

    @Override
    @Transactional
    public Recommendation update(
            Recommendation recommendation,
            RecommendationDto recommendationDto
    ) {
        recommendation.setUpdatedAt(LocalDateTime.now());
        recommendation.setContent(recommendationDto.getContent());
        return recommendationRepository.save(recommendation);
    }

    @Override
    @Transactional(readOnly = true)
    public Recommendation findByAuthorAndReceiver(User author, User receiver) {
        return recommendationRepository.findByAuthorAndReceiverOrderByIdDesc(author, receiver);
    }
}
