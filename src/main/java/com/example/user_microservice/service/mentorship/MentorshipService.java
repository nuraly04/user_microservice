package com.example.user_microservice.service.mentorship;

import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface MentorshipService {

    MentorshipRequest get(Long requestId);

    MentorshipRequest findByMenteeAndMentor(User mentee, User mentor);

    MentorshipRequest findLastByMentee(User mentee);

    void requestMentorship(MentorshipRequest mentorshipRequest);

    List<MentorshipRequest> getRequests(BooleanBuilder predicate);

    void updateStatus(MentorshipRequest request, RequestStatusEnum statusEnum);
}
