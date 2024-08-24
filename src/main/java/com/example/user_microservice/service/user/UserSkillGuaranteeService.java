package com.example.user_microservice.service.user;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;

public interface UserSkillGuaranteeService {

    UserSkillGuarantee create(User user, User guarantor, Skill skill);
}
