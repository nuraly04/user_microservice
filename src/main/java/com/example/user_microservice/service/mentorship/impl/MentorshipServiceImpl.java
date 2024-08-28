package com.example.user_microservice.service.mentorship.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.mentorship.MentorshipRepository;
import com.example.user_microservice.service.mentorship.MentorshipService;
import com.example.user_microservice.utils.enums.RequestStatusEnum;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipServiceImpl implements MentorshipService {

    MentorshipRepository mentorshipRepository;

    @Override
    @Transactional
    public MentorshipRequest get(Long requestId) {
        return mentorshipRepository.findById(requestId).orElseThrow(
                () -> new DataNotFoundException(MentorshipRequest.class, requestId, "id")
        );
    }

    @Override
    @Transactional
    public MentorshipRequest findByMenteeAndMentor(User mentee, User mentor) {
        return mentorshipRepository.findByMenteeAndMentor(mentee, mentor);
    }

    @Override
    @Transactional
    public MentorshipRequest findLastByMentee(User mentee) {
        return mentorshipRepository.findByMenteeOrderByIdDesc(mentee);
    }

    @Override
    @Transactional
    public void requestMentorship(MentorshipRequest request) {
        request.setStatus(RequestStatusEnum.PENDING);
        mentorshipRepository.save(request);
    }

    @Override
    @Transactional
    public List<MentorshipRequest> getRequests(BooleanBuilder predicate) {
        List<MentorshipRequest> requests = new ArrayList<>();
        mentorshipRepository.findAll(predicate).forEach(requests::add);
        return requests;
    }

    @Override
    @Transactional
    public void updateStatus(MentorshipRequest request, RequestStatusEnum statusEnum) {
        request.setStatus(statusEnum);
        mentorshipRepository.save(request);
    }
}
