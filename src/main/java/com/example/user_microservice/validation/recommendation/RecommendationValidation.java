package com.example.user_microservice.validation.recommendation;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.service.skill.SkillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecommendationValidation {

    SkillService skillService;

    public void validateSkills(List<Long> skillIds, Long receiverId) {
    }
}
