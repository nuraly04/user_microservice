package com.example.user_microservice.dto.mentorship;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorshipRequestAddDto {

    @NotNull
    @Min(0)
    private Long mentorId;

    @NotBlank
    private String content;
}
