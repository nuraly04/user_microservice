package com.example.user_microservice.controller.skill;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.skill.SkillManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    @InjectMocks
    private SkillController skillController;

    @Mock
    private SkillManager skillManager;

    private Long userId;

    @BeforeEach
    private void setUp() {
        userId = 1L;
    }

    @Test
    public void createTest() {
        SkillDto dto = SkillDto.builder()
                .title("test skill")
                .build();
        skillController.create(dto);
        verify(skillManager, Mockito.times(1)).create(dto);
    }

    @Test
    public void getUserSkillsTest() {
        skillController.getUserSkills(userId);
        verify(skillManager, Mockito.times(1)).getUserSkills(userId);
    }

    @Test
    public void getUserSkillsOfferedTest() {
        skillController.getUserSkillsOffered(userId);
        verify(skillManager, Mockito.times(1)).getUserSkillsOffered(userId);
    }

    @Test
    public void acquireSkillFromOffersTest() {
        Long skillId = 1L;
        skillController.acquireSkillFromOffers(skillId, userId);
        verify(skillManager, Mockito.times(1)).acquireSkillFromOffers(skillId, userId);
    }
}
