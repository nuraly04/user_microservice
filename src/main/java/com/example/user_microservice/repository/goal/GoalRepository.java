package com.example.user_microservice.repository.goal;

import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.GoalStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>, QuerydslPredicateExecutor<Goal> {

    List<Goal> findAllByMentorAndStatus(User mentor, GoalStatusEnum statusEnum);

    List<Goal> findAllByParent(Goal parent);

    long countByMentorAndStatus(User mentor, GoalStatusEnum statusEnum);
}
