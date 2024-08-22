package com.example.user_microservice.validation.skill;

import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillValidation {

    SkillService skillService;
    SkillOfferService skillOfferService;

    private static final int MAX_COUNT_OFFERED_SKILL = 3;

    public void validationSkill(Long skillId, Long userId) {
        if (Objects.nonNull(skillService.findBySkillIdAndByUserId(skillId, userId))) {
            throw new IllegalArgumentException(String.format("Скилл под id:%s уже имеет пользователь под id:%s", skillId, userId));
        }
    }

    public void validationCountOfferedSkill(Long skillId, Long userId) {
        Long offeredSkill = skillOfferService.countOfferedBySkillAndUser(skillId, userId);
        if (offeredSkill > MAX_COUNT_OFFERED_SKILL) {
            throw new IllegalArgumentException(String.format("Превышено количество предложенного скила id:%s для полбзователя id:%s", skillId, userId));
        }
    }
}
