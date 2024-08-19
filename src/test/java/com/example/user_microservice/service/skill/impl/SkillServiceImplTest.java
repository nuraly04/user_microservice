package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.skill.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillMapper skillMapper;

    @Test
    public void createTest() {
        SkillDto dto = SkillDto.builder()
                .title("title")
                .build();

        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("title");
        Mockito.when(skillMapper.toEntity(dto)).thenReturn(skill);
        skillService.create(dto);
        Mockito.verify(skillRepository, Mockito.times(1)).save(skill);
    }
}