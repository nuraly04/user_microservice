package com.example.user_microservice.dto.goal;

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
public class GoalFilterDto {

    private String title;
    private String description;
    private List<Long> skillIds;
    private Long mentor;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}
