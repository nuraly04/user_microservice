package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
import com.example.user_microservice.repository.user.UserSkillGuaranteeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserSkillGuaranteeServiceTest {

    @InjectMocks
    private UserSkillGuaranteeServiceImpl userSkillGuaranteeService;

    @Mock
    private UserSkillGuaranteeRepository userSkillGuaranteeRepository;

    private UserSkillGuarantee skillGuarantee;
    private User user;
    private User guarantor;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(2L);
        guarantor = new User();
        guarantor.setId(8L);
        skillGuarantee = new UserSkillGuarantee();
        skillGuarantee.setId(3L);
        skillGuarantee.setUser(user);
        skillGuarantee.setGuarantor(guarantor);
    }

    @Test
    public void createTest() {
        Skill skill = new Skill();

        userSkillGuaranteeService.create(skillGuarantee);

        verify(userSkillGuaranteeRepository, Mockito.times(1)).save(skillGuarantee);
    }

    @Test
    public void deleteTest() {

        userSkillGuaranteeService.delete(skillGuarantee);

        verify(userSkillGuaranteeRepository, Mockito.times(1)).delete(skillGuarantee);
    }

    @Test
    public void findBySkillsAndGuarantorAndUserTest() {

        List<Skill> skills = getSkills();

        userSkillGuaranteeService.findBySkillsAndGuarantorAndUser(user, guarantor, skills);

        verify(userSkillGuaranteeRepository, Mockito.times(1)).findAllByUserAndGuarantorAndSkillIn(user, guarantor, skills);
    }

    private List<Skill> getSkills() {
        Skill skill1 = new Skill();
        skill1.setId(43L);
        Skill skill2 = new Skill();
        skill2.setId(53L);
        Skill skill3 = new Skill();
        skill3.setId(21L);
        return Arrays.asList(skill1, skill2, skill3);
    }
}
