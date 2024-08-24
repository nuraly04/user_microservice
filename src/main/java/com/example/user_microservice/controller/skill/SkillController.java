package com.example.user_microservice.controller.skill;

import com.example.user_microservice.dto.skill.SkillCandidateDto;
import com.example.user_microservice.dto.skill.SkillDto;
import com.example.user_microservice.manager.skill.SkillManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.SKILL)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkillController {

    SkillManager skillManager;

    @PostMapping()
    public SkillDto create(@Valid @RequestBody SkillDto skillDto) {
        return skillManager.create(skillDto);
    }

    @GetMapping("/{userId}")
    public List<SkillDto> getUserSkills(@PathVariable Long userId) {
        return skillManager.getUserSkills(userId);
    }

    @GetMapping("/{userId}/offered")
    public List<SkillCandidateDto> getUserSkillsOffered(@PathVariable Long userId) {
        return skillManager.getUserSkillsOffered(userId);
    }

    @PostMapping("/{userId}/offered/{skillId}")
    public SkillDto acquireSkillFromOffers(@PathVariable Long skillId, @PathVariable Long userId) {
        return skillManager.acquireSkillFromOffers(skillId, userId);
    }

}