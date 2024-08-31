package com.example.user_microservice.service.goal.impl;

import com.example.user_microservice.dto.goal.GoalFilterDto;
import com.example.user_microservice.dto.goal.GoalUpdateRequestDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.goal.Goal;
import com.example.user_microservice.model.goal.QGoal;
import com.example.user_microservice.model.skill.Skill;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.goal.GoalRepository;
import com.example.user_microservice.service.goal.GoalService;
import com.example.user_microservice.utils.enums.GoalStatusEnum;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.user_microservice.model.goal.QGoal.goal;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoalServiceImpl implements GoalService {

    GoalRepository goalRepository;

    @Override
    @Transactional
    public Goal get(Long goalId) {
        return goalRepository.findById(goalId).orElseThrow(
                () -> new DataNotFoundException(Goal.class, goalId, "id")
        );
    }

    @Override
    @Transactional
    public Goal saveOrUpdate(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    @Transactional
    public Goal updateGoal(Goal goal, GoalUpdateRequestDto requestDto) {
        update(goal, requestDto);
        return goal;
    }

    @Override
    @Transactional
    public List<Goal> findGoalByMentorAndStatus(User mentor, GoalStatusEnum statusEnum) {
        return goalRepository.findAllByMentorAndStatus(mentor, statusEnum);
    }

    @Override
    @Transactional
    public List<Goal> findByParentGoal(Goal parentGoal) {
        return goalRepository.findAllByParent(parentGoal);
    }

    @Override
    @Transactional
    public List<Goal> getGoals(GoalFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        search(predicate, filterDto);
        List<Goal> goals = new ArrayList<>();
        goalRepository.findAll(predicate).forEach(goals::add);
        return goals;
    }


    @Override
    @Transactional(readOnly = true)
    public long countGoalByMentorAndStatus(User mentor, GoalStatusEnum statusEnum) {
        return goalRepository.countByMentorAndStatus(mentor, statusEnum);
    }

    @Override
    @Transactional
    public void deleteGoal(User deletedBy, Goal goal) {
        goal.setDeletedAt(LocalDateTime.now());
        goal.setDeletedBy(deletedBy);
        goalRepository.save(goal);
    }

    @Override
    @Transactional
    public void updateSkills(List<Skill> skills, Goal goal) {
        goal.getSkills().removeIf(skill -> !skills.contains(skill));
        for (Skill newSkill : skills) {
            if (goal.getSkills().contains(newSkill)) {
                continue;
            }
            goal.getSkills().add(newSkill);
        }
    }

    private void update(Goal goal, GoalUpdateRequestDto requestDto) {
        if (nonNull(requestDto.getTitle())) {
            goal.setTitle(requestDto.getTitle());
        }
        if (nonNull(requestDto.getDescription())) {
            goal.setDescription(requestDto.getDescription());
        }
        if (nonNull(requestDto.getParentId())) {
            Goal parentGoal = get(requestDto.getParentId());
            goal.setParent(parentGoal);
        }
    }

    private void search(BooleanBuilder predicate, GoalFilterDto filterDto) {
        if (nonNull(filterDto.getTitle())) {
            predicate.and(goal.title.containsIgnoreCase(filterDto.getTitle()));
        }
        if (nonNull(filterDto.getDescription())) {
            predicate.and(goal.description.containsIgnoreCase(filterDto.getDescription()));
        }
        if (nonNull(filterDto.getSkillIds())) {
            predicate.and(goal.skills.any().id.in(filterDto.getSkillIds()));
        }
        if (nonNull(filterDto.getMentor())) {
            predicate.and(goal.mentor.id.in(filterDto.getMentor()));
        }
        if (nonNull(filterDto.getDateFrom())) {
            if (nonNull(filterDto.getDateTo())) {
                predicate.and(goal.createdAt.between(filterDto.getDateFrom(), filterDto.getDateTo()));
            } else predicate.and(goal.createdAt.between(filterDto.getDateFrom(), LocalDateTime.now()));
        }
    }
}
