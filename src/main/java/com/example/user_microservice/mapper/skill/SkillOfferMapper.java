package com.example.user_microservice.mapper.skill;

import com.example.user_microservice.dto.skill.SkillOfferDto;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillOfferMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "skill", target = "skill")
    @Mapping(source = "recommendation", target = "recommendation")
    SkillOffer toCreate(Skill skill, Recommendation recommendation);

    @Mapping(source = "skill.id", target = "skill.id")
    @Mapping(source = "skill.name", target = "skill.title")
    @Mapping(source = "recommendation.id", target = "recommendationId")
    SkillOfferDto toDto(SkillOffer skillOffer);
}
