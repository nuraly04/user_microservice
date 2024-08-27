package com.example.user_microservice.repository.recommendation;

import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    Recommendation findByAuthorAndReceiverOrderByIdDesc(User author, User receiver);

    List<Recommendation> findByReceiver(User receiver);

    List<Recommendation> findByAuthor(User author);
}
