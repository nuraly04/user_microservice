package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.repository.skill.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    private SkillRepository skillRepository;

    private Long skillId;
    private Long userId;
    private Skill skill;

    @BeforeEach
    public void setUp() {
        skillId = 1L;
        userId = 1L;
        skill = new Skill();
        skill.setId(1L);
    }

    @Test
    public void createTest() {

        skillService.create(skill);

        verify(skillRepository, Mockito.times(1)).save(skill);
    }

    @Test
    public void getUserSkillsTest() {

        List<Skill> skills = getSkills();

        when(skillRepository.findAllByUserId(userId)).thenReturn(skills);

        skillService.getUserSkills(userId);

        verify(skillRepository, Mockito.times(1)).findAllByUserId(userId);
    }

    @Test
    public void getUserSkillsOfferedTest() {

        List<Skill> skills = getSkills();

        when(skillRepository.findAllOfferedByUserId(userId)).thenReturn(skills);

        skillService.getUserSkillsOffered(userId);

        verify(skillRepository, Mockito.times(1)).findAllOfferedByUserId(userId);
    }

    @Test
    public void getPositiveTest() {

        when(skillRepository.findById(skillId)).thenReturn(Optional.of(skill));

        skillService.get(skillId);

        verify(skillRepository, Mockito.times(1)).findById(skillId);
    }

    @Test
    public void getNegativeTest() {

        assertThrows(DataNotFoundException.class, () -> skillService.get(skillId));
    }

    @Test
    public void findBySkillIdAndByUserIdTest() {

        when(skillService.findBySkillIdAndByUserId(skillId, userId)).thenReturn(skill);

        skillService.findBySkillIdAndByUserId(skillId, userId);

        verify(skillRepository, Mockito.times(1)).findBySkillIdAndByUserId(skillId, userId);
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