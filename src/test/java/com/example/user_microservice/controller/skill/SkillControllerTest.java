package com.example.user_microservice.controller.skill;

import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.skill.SkillManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    @InjectMocks
    private SkillController skillController;

    @Mock
    private SkillManager skillManager;

    @Test
    public void test() {
        SkillDto dto = SkillDto.builder()
                .title("dfgsfgd")
                        .build();
        skillController.create(dto);
        Mockito.verify(skillManager, Mockito.times(1)).create(dto);
    }

    @Test
    public void test1() {

    }
}
