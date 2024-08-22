package com.example.user_microservice.manager.skill.impl;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.service.skill.SkillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SkillManagerImplTest {

    @InjectMocks
    private SkillManagerImpl skillManager;

    @Mock
    private SkillService skillService;

    @Mock
    private SkillMapper skillMapper;

    @Test
    public void create() {
        SkillDto dto = SkillDto.builder()
                .title("Java developer")
                .build();

        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("Java developer");

        Mockito.when(skillService.create(skill)).thenReturn(skill);

        skillManager.create(dto);
        Mockito.verify(skillService, Mockito.times(1)).create(skill);
        Mockito.verify(skillMapper, Mockito.times(1)).toDto(skill);
    }
}
