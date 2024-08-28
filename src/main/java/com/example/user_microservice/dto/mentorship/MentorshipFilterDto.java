package com.example.user_microservice.dto.mentorship;

import com.example.user_microservice.utils.enums.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorshipFilterDto {

    private Long menteeId;
    private String content;
    private RequestStatusEnum status;
}
