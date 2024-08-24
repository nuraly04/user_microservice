package com.example.user_microservice.repository.user;

import com.example.user_microservice.model.user.UserSkillGuarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillGuaranteeRepository extends JpaRepository<UserSkillGuarantee, Long> {
}
