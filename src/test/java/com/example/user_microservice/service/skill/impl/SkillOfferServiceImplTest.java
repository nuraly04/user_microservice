package com.example.user_microservice.service.skill.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.repository.skill.SkillOfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillOfferServiceImplTest {

    @InjectMocks
    private SkillOfferServiceImpl skillOfferService;

    @Mock
    private SkillOfferRepository skillOfferRepository;

    private Long skillOfferId;
    private Long skillId;
    private Long userId;
    private SkillOffer skillOffer;

    @BeforeEach
    public void setUp() {
        skillId = 1L;
        userId = 1L;
        skillOfferId = 1L;
        skillOffer = new SkillOffer();
    }

    @Test
    public void getPositiveTest() {

        Skill skill = new Skill();
        skill.setId(1L);

        skillOffer.setSkill(skill);

        when(skillOfferRepository.findById(skillOfferId)).thenReturn(Optional.ofNullable(skillOffer));

        skillOfferService.get(skillOfferId);

        verify(skillOfferRepository, Mockito.times(1)).findById(skillOfferId);
    }

    @Test
    public void getNegativeTest() {

        assertThrows(DataNotFoundException.class, () -> skillOfferService.get(skillOfferId));
    }

    @Test
    public void findBySkillIdAndUserIdTest() {

        when(skillOfferRepository.findBySkillIdAndUserId(skillId, userId)).thenReturn(skillOffer);

        skillOfferService.findBySkillIdAndUserId(skillId, userId);

        verify(skillOfferRepository, Mockito.times(1)).findBySkillIdAndUserId(skillId, userId);
    }

    @Test
    public void countOfferedBySkillAndUser() {

        when(skillOfferRepository.countBySkillIdAndByUserId(skillId, userId)).thenReturn(1L);

        skillOfferService.countOfferedBySkillAndUser(skillId, userId);

        verify(skillOfferRepository, Mockito.times(1)).countBySkillIdAndByUserId(skillId, userId);
    }
}