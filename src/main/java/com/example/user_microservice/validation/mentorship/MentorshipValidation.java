package com.example.user_microservice.validation.mentorship;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.mentorship.MentorshipRequest;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.mentorship.MentorshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipValidation {

    MentorshipService mentorshipService;

    private static final Long MIN_AVAILABLE_MONTH = 3L;

    public void validateAvailableRequest(User mentee, User mentor) {
        MentorshipRequest request = mentorshipService.findByMenteeAndMentor(mentee, mentor);
        if (Objects.nonNull(request)) {
            throw new DataValidationException(String.format("%s уже является ментором для - %s", mentor, mentee));
        }
        MentorshipRequest request1 = mentorshipService.findLastByMentee(mentee);
        if (Objects.nonNull(request1)) {
            LocalDateTime createdAt = request1.getCreatedAt();
            LocalDateTime available = LocalDateTime.now().minusMonths(MIN_AVAILABLE_MONTH);

            if (createdAt.isAfter(available)) {
                throw new DataValidationException(String.format("%s запрос на менторство можно запросить раз в 3 месяца", mentee));
            }
        }
    }
}
