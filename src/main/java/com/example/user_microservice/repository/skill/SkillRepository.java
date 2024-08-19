package com.example.user_microservice.repository.skill;

import com.example.user_microservice.model.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {


}