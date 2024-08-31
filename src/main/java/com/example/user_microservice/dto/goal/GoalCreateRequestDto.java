package com.example.user_microservice.dto.goal;

import com.example.user_microservice.model.goal.Goal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalCreateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Long parentId;

    @NotEmpty
    private List<Long> skillIds;
}
