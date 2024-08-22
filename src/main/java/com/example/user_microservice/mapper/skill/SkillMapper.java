package com.example.user_microservice.mapper.skill;

import com.example.user_microservice.dto.skill.SkillCandidateDto;
import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.model.skill.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SkillMapper {

    @Mapping(source = "title", target = "name")
    Skill toEntity(SkillDto dto);

    @Mapping(source = "name", target = "title")
    SkillDto toDto(Skill entity);

    @Mapping(target = "skillDto.id", source = "entity.id")
    @Mapping(target = "skillDto.title", source = "entity.name")
    SkillCandidateDto toCandidateDto(Skill entity, Long offersAmount);
}