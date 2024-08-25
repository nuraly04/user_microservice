package com.example.user_microservice.validation.skill;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillValidationTest {

    @InjectMocks
    private SkillValidation skillValidation;

    @Mock
    private SkillService skillService;

    @Mock
    private SkillOfferService skillOfferService;

    private Long skillId;
    private Long userId;
    private Skill skill;

    @BeforeEach
    private void setUp() {
        skillId = 1L;
        userId = 1L;
        skill = new Skill();
        skill.setId(1L);
    }

    @Test
    public void validationSkillNegativeTest() {

        when(skillService.findBySkillIdAndByUserId(skillId, userId)).thenReturn(skill);

        assertThrows(IllegalArgumentException.class, () -> skillValidation.validationSkill(skillId, userId));

        verify(skillService, Mockito.times(1)).findBySkillIdAndByUserId(skillId, userId);
    }

    @Test
    public void validationSkillPositiveTest() {

        when(skillService.findBySkillIdAndByUserId(skillId, userId)).thenReturn(null);

        skillValidation.validationSkill(skillId, userId);

        verify(skillService, Mockito.times(1)).findBySkillIdAndByUserId(skillId, userId);
    }

    @Test
    public void validationCountOfferedSkillPositiveTest() {

        when(skillOfferService.countOfferedBySkillAndUser(skillId, userId)).thenReturn(1L);

        skillValidation.validationCountOfferedSkill(skillId, userId);

        verify(skillOfferService, Mockito.times(1)).countOfferedBySkillAndUser(skillId, userId);
    }

    @Test
    public void validationCountOfferedSkillNegativeTest() {

        when(skillOfferService.countOfferedBySkillAndUser(skillId, userId)).thenReturn(4L);

        assertThrows(IllegalArgumentException.class, () -> skillValidation.validationCountOfferedSkill(skillId, userId));
    }
}
