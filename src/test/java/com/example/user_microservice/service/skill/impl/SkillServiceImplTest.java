package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.skill.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;f

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    private SkillRepository skillRepository;

    @Test
    public void createTest() {

        Skill skill = new Skill();
        skill.setName("title");
        skillService.create(skill);
        verify(skillRepository, Mockito.times(1)).save(skill);
    }

    @Test
    public void getUserSkillsTest() {

        Long userId = 1L;

        List<Skill> skills = getSkills();

        when(skillRepository.findAllByUserId(userId)).thenReturn(skills);

        skillService.getUserSkillsOffered(userId);

        verify(skillRepository, Mockito.times(1)).findAllByUserId(userId);
    }

    @Test
    public void getUserSkillsOfferedTest() {

        Long userId = 1L;

        List<Skill> skills = getSkills();

        when(skillRepository.findAllOfferedByUserId(userId)).thenReturn(skills);

        skillService.getUserSkillsOffered(userId);

        verify(skillRepository, Mockito.times(1)).findAllOfferedByUserId(userId);
    }

    @Test
    public void getPositiveTest() {
        Long skillId = 1L;

        Skill skill = new Skill();
        skill.setId(1L);

        when(skillRepository.findById(skillId)).thenReturn(Optional.of(skill));

        skillService.get(skillId);

        verify(skillRepository, Mockito.times(1)).findById(skillId);
    }

    @Test
    public void getNegativeTest() {

        Long skillId = 1L;

        when(skillRepository.findById(skillId)).thenThrow(() -> DataNotFoundException.class);
    }

    private List<Skill> getSkills() {
        Skill skill = new Skill();
        skill.setId(1L);

        Skill skill1 = new Skill();
        skill1.setId(2L);

        Skill skill2 = new Skill();
        skill2.setId(3L);

        return List.of(
                skill1,
                skill,
                skill2
        );
    }
}