package com.example.user_microservice.mapper.recommendation;

import com.example.user_microservice.dto.recommendation.CreateRecommendationRequestDto;
import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.dto.skill.SkillOfferDto;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecommendationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skillOffers", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "author", target = "author")
    @Mapping(source = "receiver", target = "receiver")
    Recommendation toEntity(CreateRecommendationRequestDto recommendationDto, User author, User receiver);

    @Mapping(source = "recommendation.id", target = "id")
    @Mapping(source = "skillOffers", target = "skills")
    @Mapping(source = "authorDto", target = "author")
    @Mapping(source = "receiverDto", target = "receiver")
    RecommendationDto toDto(Recommendation recommendation, List<SkillOfferDto> skillOffers, UserDto authorDto, UserDto receiverDto);
}
