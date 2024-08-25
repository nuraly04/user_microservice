package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
import com.example.user_microservice.repository.user.UserSkillGuaranteeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserSkillGuaranteeServiceTest {

    @InjectMocks
    private UserSkillGuaranteeServiceImpl userSkillGuaranteeService;

    @Mock
    private UserSkillGuaranteeRepository userSkillGuaranteeRepository;

    @Test
    public void createTest() {

        User user = new User();
        User guarantor = new User();
        Skill skill = new Skill();

        UserSkillGuarantee userSkillGuarantee = new UserSkillGuarantee();

        userSkillGuaranteeService.create(user, guarantor, skill);

        Mockito.verify(userSkillGuaranteeRepository, Mockito.times(1)).save(userSkillGuarantee);
    }
}
