package com.example.user_microservice.dto.recommendation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecommendationRequestDto {

    @NotNull
    private Long authorId;

    @NotNull
    private Long receiverId;

    @NotBlank
    private String content;

    private List<Long> skillOffers;
}
