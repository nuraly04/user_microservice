package com.example.user_microservice.manager.recommendation.impl;

import com.example.user_microservice.dto.recommendation.RecommendationDto;
import com.example.user_microservice.dto.skill.SkillOfferDto;
import com.example.user_microservice.mapper.recommendation.RecommendationMapper;
import com.example.user_microservice.mapper.skill.SkillOfferMapper;
import com.example.user_microservice.mapper.user.UserSkillGuaranteeMapper;
import com.example.user_microservice.model.recommendation.Recommendation;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.skill.SkillOffer;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
import com.example.user_microservice.service.recommendation.RecommendationService;
import com.example.user_microservice.service.skill.SkillOfferService;
import com.example.user_microservice.service.skill.SkillService;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.service.user.UserSkillGuaranteeService;
import com.example.user_microservice.validation.recommendation.RecommendationValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationManagerImplTest {

    @InjectMocks
    private RecommendationManagerImpl recommendationManager;

    @Mock
    private UserService userService;

    @Mock
    private SkillService skillService;

    @Mock
    private SkillOfferService skillOfferService;

    @Mock
    private SkillOfferMapper skillOfferMapper;

    @Mock
    private UserSkillGuaranteeMapper skillGuaranteeMapper;

    @Mock
    private RecommendationMapper recommendationMapper;

    @Mock
    private UserSkillGuaranteeService guaranteeService;

    @Mock
    private RecommendationService recommendationService;

    @Mock
    private RecommendationValidation recommendationValidation;

    private User author;
    private Long authorId;
    private User receiver;
    private Long receiverId;
    private Recommendation recommendation;
    private Long recommendationId;
    private RecommendationDto recommendationDto;
    private Skill skill;

    @BeforeEach
    public void setUp() {
        author = new User();
        author.setId(42L);
        author.setName("John");

        authorId = 42L;

        receiver = new User();
        receiver.setId(13L);
        receiver.setName("Bob");

        receiverId = 13L;

        recommendation = new Recommendation();
        recommendation.setId(21L);
        recommendation.setAuthor(author);
        recommendation.setReceiver(receiver);
        recommendation.setContent("Java Spring");

        recommendationId = 21L;

        recommendationDto = RecommendationDto.builder()
                .id(21L)
                .authorId(42L)
                .receiverId(13L)
                .content("Java Spring")
                .skillOffers(getSkillOfferDtos())
                .build();

        skill = new Skill();
        skill.setId(97L);
    }

    @Test
    public void giveRecommendationTest() {

        ArgumentCaptor<Skill> skillArgumentCaptor = ArgumentCaptor.forClass(Skill.class);
        ArgumentCaptor<UserSkillGuarantee> guaranteeArgumentCaptor = ArgumentCaptor.forClass(UserSkillGuarantee.class);

        List<Long> skillIds = getSkillIds();
        List<Skill> skills = getSkills();
        List<SkillOffer> skillOffers = getSkillOffers();
        List<UserSkillGuarantee> guarantees = getSkillGuarantees();

        when(userService.get(authorId)).thenReturn(author);
        when(userService.get(receiverId)).thenReturn(receiver);
        when(skillService.findBySkillIds(skillIds)).thenReturn(skills);
        when(guaranteeService.findBySkillsAndGuarantorAndUser(receiver, author, skills)).thenReturn(guarantees);
        when(recommendationMapper.toEntity(recommendationDto, author, receiver)).thenReturn(recommendation);
        when(recommendationService.create(recommendation)).thenReturn(recommendation);
        when(recommendationService.findByAuthorAndReceiver(author, receiver)).thenReturn(recommendation);

        when(skillOfferMapper.toCreate(skills.get(0), recommendation)).thenReturn(skillOffers.get(0));
        when(skillOfferMapper.toCreate(skills.get(1), recommendation)).thenReturn(skillOffers.get(1));
        when(skillOfferMapper.toCreate(skills.get(2), recommendation)).thenReturn(skillOffers.get(2));

//        when(skillOfferService.create(skillOffers.get(0))).thenReturn(skillOffers.get(0));
//        when(skillOfferService.create(skillOffers.get(1))).thenReturn(skillOffers.get(1));
//        when(skillOfferService.create(skillOffers.get(2))).thenReturn(skillOffers.get(2));

        recommendationManager.giveRecommendation(recommendationDto);

        verify(userService, Mockito.times(1)).get(authorId);
        verify(userService, Mockito.times(1)).get(receiverId);
        verify(recommendationService, Mockito.times(1)).findByAuthorAndReceiver(author, receiver);
        verify(recommendationValidation, Mockito.times(1)).validateAvailableGiveRecommendation(recommendation);
        verify(skillService, Mockito.times(1)).findBySkillIds(skillIds);
        verify(guaranteeService, Mockito.times(1)).findBySkillsAndGuarantorAndUser(receiver, author, skills);
        verify(recommendationMapper, Mockito.times(1)).toEntity(recommendationDto, author, receiver);
        verify(recommendationService, Mockito.times(1)).create(recommendation);
        verify(skillOfferMapper, Mockito.times(1)).toCreate(skills.get(0), recommendation);
        verify(skillOfferMapper, Mockito.times(1)).toCreate(skills.get(1), recommendation);
        verify(skillOfferMapper, Mockito.times(1)).toCreate(skills.get(2), recommendation);
        verify(skillOfferService, Mockito.times(1)).create(skillOffers.get(0));
        verify(skillOfferService, Mockito.times(1)).create(skillOffers.get(1));
        verify(skillOfferService, Mockito.times(1)).create(skillOffers.get(2));
        verify(skillGuaranteeMapper, Mockito.times(3)).create(eq(receiver), eq(author), skillArgumentCaptor.capture());
        verify(guaranteeService, Mockito.times(3)).create(guaranteeArgumentCaptor.capture());

        List<UserSkillGuarantee> captorGuarantees = guaranteeArgumentCaptor.getAllValues();
        assertEquals(3, captorGuarantees.size());

        List<Skill> capturedSkills = skillArgumentCaptor.getAllValues();
        assertEquals(3, capturedSkills.size());
        assertEquals(skills.get(0), capturedSkills.get(0));
        assertTrue(skills.containsAll(capturedSkills));
    }

    @Test
    public void getRecommendationTest() {

        when(recommendationService.get(recommendationId)).thenReturn(recommendation);

        recommendationManager.getRecommendation(recommendationId);

        verify(recommendationService, Mockito.times(1)).get(recommendationId);
        verify(recommendationMapper, Mockito.times(1)).toDto(recommendation);
    }

    @Test
    public void getRecommendationsTest() {

        List<Recommendation> recommendations = getRecommendations();

        when(userService.get(receiverId)).thenReturn(receiver);
        when(recommendationService.findByReceiver(receiver)).thenReturn(recommendations);
        recommendationManager.getRecommendations(receiverId);

        verify(userService, Mockito.times(1)).get(receiverId);
        verify(recommendationService, Mockito.times(1)).findByReceiver(receiver);
        verify(recommendationMapper, Mockito.times(1)).toDto(recommendations.get(0));
        verify(recommendationMapper, Mockito.times(1)).toDto(recommendations.get(1));
    }

    @Test
    public void getAllGivenRecommendationTest() {

        List<Recommendation> recommendations = getRecommendations();

        when(userService.get(authorId)).thenReturn(author);
        when(recommendationService.findByAuthor(author)).thenReturn(recommendations);

        recommendationManager.getAllGivenRecommendation(authorId);

        verify(userService, Mockito.times(1)).get(authorId);
        verify(recommendationService, Mockito.times(1)).findByAuthor(author);
        verify(recommendationMapper, Mockito.times(1)).toDto(recommendations.get(0));
        verify(recommendationMapper, Mockito.times(1)).toDto(recommendations.get(1));
    }

    @Test
    public void updateRecommendationTest() {

        when(recommendationService.get(recommendationId)).thenReturn(recommendation);

        recommendationManager.updateRecommendation(recommendationId, recommendationDto);

        verify(recommendationService, Mockito.times(1)).update(recommendation, recommendationDto);
    }

    @Test
    public void deleteRecommendationTest() {

        List<SkillOffer> skillOffers = getSkillOffers();
        List<UserSkillGuarantee> skillGuarantees = getSkillGuarantees();
        List<Skill> skills = skillOffers.stream()
                .map(SkillOffer::getSkill)
                .toList();

        when(recommendationService.get(recommendationId)).thenReturn(recommendation);
        when(skillOfferService.findByRecommendation(recommendation)).thenReturn(skillOffers);
        when(guaranteeService.findBySkillsAndGuarantorAndUser(recommendation.getReceiver(), recommendation.getAuthor(), skills)).thenReturn(skillGuarantees);

        recommendationManager.deleteRecommendation(recommendationId);

        verify(recommendationService, Mockito.times(1)).get(recommendationId);
        verify(skillOfferService, Mockito.times(1)).findByRecommendation(recommendation);
        verify(skillOfferService, Mockito.times(1)).delete(skillOffers.get(0));
        verify(skillOfferService, Mockito.times(1)).delete(skillOffers.get(1));
        verify(guaranteeService, Mockito.times(1)).findBySkillsAndGuarantorAndUser(recommendation.getReceiver(), recommendation.getAuthor(), skills);
        verify(guaranteeService, Mockito.times(1)).delete(skillGuarantees.get(0));
        verify(guaranteeService, Mockito.times(1)).delete(skillGuarantees.get(1));
    }

    private List<Recommendation> getRecommendations() {
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setId(43L);
        Recommendation recommendation2 = new Recommendation();
        recommendation2.setId(213L);
        return Arrays.asList(recommendation1, recommendation2);
    }

    private List<RecommendationDto> getRecommendationDtos() {
        RecommendationDto recommendation1 = RecommendationDto.builder()
                .id(43L)
                .build();
        RecommendationDto recommendation2 = RecommendationDto.builder()
                .id(213L)
                .build();
        return Arrays.asList(recommendation1, recommendation2);
    }

    private List<Long> getSkillIds() {
        return Arrays.asList(54L, 92L, 40L);
    }

    private List<SkillOfferDto> getSkillOfferDtos() {
        SkillOfferDto skillOfferDto1 = SkillOfferDto.builder()
                .skillId(54L)
                .build();
        SkillOfferDto skillOfferDto2 = SkillOfferDto.builder()
                .skillId(92L)
                .build();
        SkillOfferDto skillOfferDto3 = SkillOfferDto.builder()
                .skillId(40L)
                .build();
        return Arrays.asList(skillOfferDto1, skillOfferDto2, skillOfferDto3);
    }

    private List<Skill> getSkills() {
        Skill skill1 = new Skill();
        skill1.setId(54L);
        Skill skill2 = new Skill();
        skill2.setId(92L);
        Skill skill3 = new Skill();
        skill3.setId(40L);

        return Arrays.asList(skill1, skill2, skill3);
    }

    private List<Skill> getSkillsList() {
        Skill skill1 = new Skill();
        skill1.setId(4L);
        Skill skill2 = new Skill();
        skill2.setId(22L);
        Skill skill3 = new Skill();
        skill3.setId(42L);

        return Arrays.asList(skill1, skill2, skill3);
    }

    private List<UserSkillGuarantee> getSkillGuarantees() {
        UserSkillGuarantee skillGuarantee1 = new UserSkillGuarantee();
        skillGuarantee1.setId(63L);
        skillGuarantee1.setSkill(new Skill());
        UserSkillGuarantee skillGuarantee2 = new UserSkillGuarantee();
        skillGuarantee2.setId(89L);
        skillGuarantee2.setSkill(new Skill());
        UserSkillGuarantee skillGuarantee3 = new UserSkillGuarantee();
        skillGuarantee3.setId(48L);
        skillGuarantee3.setSkill(new Skill());
        return Arrays.asList(skillGuarantee1, skillGuarantee2, skillGuarantee3);
    }

    private List<SkillOffer> getSkillOffers() {
        SkillOffer skillOffer1 = new SkillOffer();
        skillOffer1.setId(23L);
        skillOffer1.setRecommendation(recommendation);
        SkillOffer skillOffer2 = new SkillOffer();
        skillOffer2.setId(34L);
        skillOffer2.setRecommendation(recommendation);
        SkillOffer skillOffer3 = new SkillOffer();
        skillOffer3.setId(21L);
        skillOffer3.setRecommendation(recommendation);

        return Arrays.asList(skillOffer1, skillOffer2, skillOffer3);
    }

}