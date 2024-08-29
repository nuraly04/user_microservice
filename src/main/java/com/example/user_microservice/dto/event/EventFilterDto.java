package com.example.user_microservice.dto.event;

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
public class EventFilterDto {

    private Long ownerId;
    private Long cityId;
    private Long minAttendees;
    private Long maxAttendees;
    private List<Long> skillIds;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime afterCreatedAt;
    private LocalDateTime beforeCreatedAt;
}
