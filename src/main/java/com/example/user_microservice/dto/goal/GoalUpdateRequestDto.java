package com.example.user_microservice.dto.goal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalUpdateRequestDto {

    private String title;
    private String description;
    private List<Long> skillIds;
    private Long parentId;
}
