package com.example.user_microservice.mapper.skill;

import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillOfferMapper {

    @Mapping(source = "skill", target = "skill")
    @Mapping(source = "recommendation", target = "recommendation")
    SkillOffer toEntity(Skill skill, Recommendation recommendation);
}
