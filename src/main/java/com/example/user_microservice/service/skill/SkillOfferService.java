package com.example.user_microservice.service.skill;

public interface SkillOfferService {

    Long countOfferedBySkillAndUser(Long skillId, Long userId);
}
