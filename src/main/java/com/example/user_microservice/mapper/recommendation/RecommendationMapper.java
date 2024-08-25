package com.example.user_microservice.mapper.recommendation;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecommendationMapper {

    @Mapping(source = "author", target = "author")
    @Mapping(source = "receiver", target = "receiver")
    Recommendation toEntity(RecommendationDto recommendationDto, User author, User receiver);

    @Mapping(source = "recommendation.author.id", target = "authorId")
    @Mapping(source = "recommendation.receiver.id", target = "receiverId")
    RecommendationDto toDto(Recommendation recommendation);
}
