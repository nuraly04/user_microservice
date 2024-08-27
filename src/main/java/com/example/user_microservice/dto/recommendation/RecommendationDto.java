package com.example.user_microservice.dto.recommendation;

import com.example.user_microservice.dto.skill.SkillOfferDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDto {

    private Long id;

    @NotNull
    private Long authorId;

    @NotNull
    private Long receiverId;

    @NotBlank
    private String content;

    private List<SkillOfferDto> skillOffers;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}