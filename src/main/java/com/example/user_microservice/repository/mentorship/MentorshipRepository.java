package com.example.user_microservice.repository.mentorship;

import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorshipRepository extends JpaRepository<MentorshipRequest, Long>, QuerydslPredicateExecutor<MentorshipRequest> {

    MentorshipRequest findByMenteeOrderByIdDesc(User mentee);

    MentorshipRequest findByMenteeAndMentor(User mentee, User mentor);
}
