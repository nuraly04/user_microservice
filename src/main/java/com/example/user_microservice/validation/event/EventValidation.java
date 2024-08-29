package com.example.user_microservice.validation.event;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EventValidation {

    public void checkSkills(List<Skill> skills, User owner) {
        Set<Skill> ownerSkills = new HashSet<>(owner.getSkills());
        if (!ownerSkills.containsAll(skills)) {
            throw new DataValidationException("Пользователь не имеет скиллы");
        }
    }
}
