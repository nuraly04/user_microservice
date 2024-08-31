package com.example.user_microservice.dto.goal;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.dto.user.UserDto;
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
public class GoalDto {

    private Long id;

    private String title;

    private UserDto mentor;

    private String description;

    private List<SkillDto> skills;

    private Long parentId;

    private LocalDateTime createdAt;
}
