package com.example.user_microservice.service.user;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;

import java.util.List;

public interface UserSkillGuaranteeService {

    UserSkillGuarantee create(UserSkillGuarantee skillGuarantee);
    void delete(UserSkillGuarantee skillGuarantee);
    List<UserSkillGuarantee> findBySkillsAndGuarantorAndUser(User user, User guarantor, List<Skill> skills);
}
