package com.example.user_microservice.manager.skill.impl;

import com.example.user_microservice.dto.skill.SkillCandidateDto;
import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.mapper.skill.SkillMapper;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.service.user.UserSkillGuaranteeService;
import com.example.user_microservice.validation.skill.SkillValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillManagerImplTest {

    @InjectMocks
    private SkillManagerImpl skillManager;

    @Mock
    private SkillService skillService;

    @Mock
    private UserService userService;

    @Mock
    private UserSkillGuaranteeService userSkillGuaranteeService;

    @Mock
    private SkillValidation skillValidation;

    @Mock
    private SkillOfferService skillOfferService;

    @Mock
    private SkillMapper skillMapper;

    private SkillDto skillDto;
    private Skill skill;
    private Long longAmount;

    @BeforeEach
    public void SetUp() {
        skillDto = SkillDto.builder()
                .id(1L)
                .title("Java Developer")
                .build();
        skill = new Skill();
        skill.setId(1L);
        skill.setName("Java developer");
        longAmount = 3L;
    }

    @Test
    public void createTest() {
        SkillDto dto = SkillDto.builder()
                .title("Java developer")
                .build();

        when(skillMapper.toEntity(dto)).thenReturn(skill);
        when(skillService.create(skill)).thenReturn(skill);

        skillManager.create(dto);
        verify(skillService, Mockito.times(1)).create(skill);
        verify(skillMapper, Mockito.times(1)).toDto(skill);
    }

    @Test
    public void getUserSkillsTest() {

        Long userId = 1L;

        List<Skill> skills = initList();
        List<SkillDto> dtos = initListDto();

        when(skillService.getUserSkills(userId)).thenReturn(skills);
        when(skillMapper.toDto(skills.get(0))).thenReturn(dtos.get(0));
        when(skillMapper.toDto(skills.get(1))).thenReturn(dtos.get(1));
        when(skillMapper.toDto(skills.get(2))).thenReturn(dtos.get(2));
        skillManager.getUserSkills(userId);

        verify(skillService, Mockito.times(1)).getUserSkills(userId);
        verify(skillMapper, Mockito.times(1)).toDto(skills.get(0));
        verify(skillMapper, Mockito.times(1)).toDto(skills.get(1));
        verify(skillMapper, Mockito.times(1)).toDto(skills.get(2));
    }

    @Test
    public void getUserSkillsOfferedTest() {

        Long userId = 1L;

        List<Skill> skills = initList();
        List<SkillCandidateDto> dtos = candidateDtos();

        when(skillService.getUserSkillsOffered(userId)).thenReturn(skills);
        when(skillOfferService.countOfferedBySkillAndUser(skills.get(0).getId(), userId)).thenReturn(longAmount);
        when(skillOfferService.countOfferedBySkillAndUser(skills.get(1).getId(), userId)).thenReturn(longAmount);
        when(skillOfferService.countOfferedBySkillAndUser(skills.get(2).getId(), userId)).thenReturn(longAmount);
        when(skillMapper.toCandidateDto(skills.get(0), longAmount)).thenReturn(dtos.get(0));
        when(skillMapper.toCandidateDto(skills.get(1), longAmount)).thenReturn(dtos.get(1));
        when(skillMapper.toCandidateDto(skills.get(2), longAmount)).thenReturn(dtos.get(2));

        skillManager.getUserSkillsOffered(userId);

        verify(skillService, Mockito.times(1)).getUserSkillsOffered(userId);
        verify(skillMapper, Mockito.times(1)).toCandidateDto(skills.get(0), longAmount);
        verify(skillMapper, Mockito.times(1)).toCandidateDto(skills.get(1), longAmount);
        verify(skillMapper, Mockito.times(1)).toCandidateDto(skills.get(2), longAmount);
    }

    @Test
    public void acquireSkillFromOffers() {

        Long userId = 1L;
        Long skillId = 1L;

        User user = new User();
        user.setId(1L);

        User guarantor = new User();
        user.setId(2L);

        Recommendation recommendation = new Recommendation();
        recommendation.setAuthor(guarantor);

        SkillOffer skillOffer = new SkillOffer();
        skillOffer.setRecommendation(recommendation);
        skillOffer.setSkill(skill);

        when(skillService.get(skillId)).thenReturn(skill);
        when(skillOfferService.findBySkillIdAndUserId(skillId, userId)).thenReturn(skillOffer);
        when(userService.get(userId)).thenReturn(user);
        when(userService.get(skillOffer.getRecommendation().getAuthor().getId())).thenReturn(guarantor);
        when(skillMapper.toDto(skill)).thenReturn(skillDto);

        skillManager.acquireSkillFromOffers(skillId, userId);

        verify(userSkillGuaranteeService, Mockito.times(1)).create(user, guarantor, skill);
        verify(skillValidation, Mockito.times(1)).validationSkill(skillId, userId);
        verify(skillValidation, Mockito.times(1)).validationCountOfferedSkill(skillId, userId);
        verify(skillOfferService, Mockito.times(1)).findBySkillIdAndUserId(skillId, userId);
        verify(skillMapper, Mockito.times(1)).toDto(skill);
    }


    private List<SkillDto> initListDto() {
        return List.of(
                new SkillDto(1L, "Java"),
                new SkillDto(2L, "JS"),
                new SkillDto(4L, "IOS")
        );
    }

    private List<SkillCandidateDto> candidateDtos() {
        return List.of(
                new SkillCandidateDto(new SkillDto(1L, "Java"), 3L),
                new SkillCandidateDto(new SkillDto(2L, "JS"), 3L),
                new SkillCandidateDto(new SkillDto(4L, "IOS"), 3L)
        );
    }

    private List<Skill> initList() {
        Skill skill1 = new Skill();
        skill1.setId(1L);
        skill1.setName("Java");
        Skill skill2 = new Skill();
        skill2.setId(2L);
        skill2.setName("Js");
        Skill skill3 = new Skill();
        skill3.setId(4L);
        skill3.setName("IOS");
        return List.of(
                skill1,
                skill2,
                skill3
        );
    }
}
