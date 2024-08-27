package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
import com.example.user_microservice.repository.user.UserSkillGuaranteeRepository;
import com.example.user_microservice.service.user.UserSkillGuaranteeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSkillGuaranteeServiceImpl implements UserSkillGuaranteeService {


    UserSkillGuaranteeRepository skillGuaranteeRepository;

    @Override
    @Transactional
    public UserSkillGuarantee create(UserSkillGuarantee skillGuarantee) {
        return skillGuaranteeRepository.save(skillGuarantee);
    }

    @Override
    @Transactional
    public void delete(UserSkillGuarantee skillGuarantee) {
        skillGuaranteeRepository.delete(skillGuarantee);
    }

    @Override
    @Transactional
    public List<UserSkillGuarantee> findBySkillsAndGuarantorAndUser(
            User user,
            User guarantor,
            List<Skill> skills
    ) {
        return skillGuaranteeRepository.findAllByUserAndGuarantorAndSkillIn(user, guarantor, skills);
    }
}
