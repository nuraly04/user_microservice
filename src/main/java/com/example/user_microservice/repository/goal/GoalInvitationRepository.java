package com.example.user_microservice.repository.goal;

import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.GoalInvitation;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalInvitationRepository extends JpaRepository<GoalInvitation, Long>, QuerydslPredicateExecutor<GoalInvitation> {

    boolean existsByInviterAndInvitedAndGoalAndStatus(User inviter, User invited, Goal goal, RequestStatusEnum statusEnum);
}
