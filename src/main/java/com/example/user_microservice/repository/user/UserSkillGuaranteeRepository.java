package com.example.user_microservice.repository.user;

import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.model.user.UserSkillGuarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillGuaranteeRepository extends JpaRepository<UserSkillGuarantee, Long> {


    List<UserSkillGuarantee> findAllByUserAndGuarantorAndSkillIn(User user, User guarantor, List<Skill> skills);
}
