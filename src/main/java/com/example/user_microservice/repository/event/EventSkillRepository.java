package com.example.user_microservice.repository.event;

import com.example.user_microservice.model.event.EventSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSkillRepository extends JpaRepository<EventSkill, Long> {
}
