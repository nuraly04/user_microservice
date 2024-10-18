package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.dto.user.UserFilterDto;
import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.contact.Contact;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.user.UserRepository;
import com.example.user_microservice.service.user.UserService;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.user_microservice.model.user.QUser.user;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException(User.class, userId, "id")
        );
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void followToAuthor(User follower, User author) {
        follower.getAuthors().add(author);
    }

    @Override
    @Transactional
    public void unfollowToAuthor(User follower, User author) {
        follower.getAuthors().remove(follower);
    }

    @Override
    @Transactional
    public void deleteMentorshipUsers(List<User> mentees, User user) {
        user.getMentees().removeAll(mentees);
    }

    @Override
    @Transactional
    public boolean existsByFollowerAndAuthor(User follower, User author) {
        return userRepository.existsByAuthorsIdEqualsAndFollowersIdEquals(follower.getId(), author.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Long countFollowers(User author) {
        return userRepository.countByAuthors(author);
    }

    @Override
    @Transactional
    public Long countAuthors(User follower) {
        return userRepository.countByFollowers(follower);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMenteesByMentorId(Long mentorId) {
        return userRepository.findMenteesByMentorId(mentorId);
    }

    @Override
    @Transactional
    public List<User> getFollowersByAuthor(User author, UserFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(user.authors.contains(author));
        search(predicate, filterDto);
        return (List<User>) userRepository.findAll(predicate);
    }

    @Override
    @Transactional
    public List<User> getAuthorsByFollower(User follower, UserFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(user.followers.contains(follower));
        search(predicate, filterDto);
        return (List<User>) userRepository.findAll(predicate);
    }

    @Override
    @Transactional
    public List<User> getUsers(UserFilterDto filterDto) {
        BooleanBuilder predicate = new BooleanBuilder();
        search(predicate, filterDto);
        return (List<User>) userRepository.findAll(predicate);
    }

    @Override
    @Transactional
    public List<User> findUsersByIds(List<Long> usersIds) {
        return userRepository.findAllById(usersIds);
    }

    @Override
    @Transactional
    public List<User> findMentorsByMenteeId(Long menteeId) {
        return userRepository.findMentorsByMenteeId(menteeId);
    }

    @Override
    @Transactional
    public List<User> findMemberByEventId(Long eventId) {
        return userRepository.findMemberByEventId(eventId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countMembersByEventId(Long eventId) {
        return userRepository.countMemberByEventId(eventId);
    }

    private void search(BooleanBuilder predicate, UserFilterDto filterDto) {
        if (nonNull(filterDto.getId())) {
            predicate.and(user.id.eq(filterDto.getId()));
        }
        if (nonNull(filterDto.getName())) {
            predicate.and(user.name.containsIgnoreCase(filterDto.getName()));
        }
        if (nonNull(filterDto.getEmail())) {
            predicate.and(user.email.containsIgnoreCase(filterDto.getEmail()));
        }
        if (nonNull(filterDto.getAbout())) {
            predicate.and(user.about.containsIgnoreCase(filterDto.getAbout()));
        }
        if (nonNull(filterDto.getCity())) {
            predicate.and(user.city.type.code.eq(filterDto.getCity()));
        }
        if (nonNull(filterDto.getCountry())) {
            predicate.and(user.city.type.code.eq(filterDto.getCountry()));
        }
        if (nonNull(filterDto.getContact())) {
            predicate.and(user.contacts.any().type.eq(filterDto.getContact()));
        }
        if (nonNull(filterDto.getPhone())) {
            predicate.and(user.phone.containsIgnoreCase(filterDto.getPhone()));
        }
        if (nonNull(filterDto.getSkill())) {
            predicate.and(user.skills.any().name.containsIgnoreCase(filterDto.getSkill()));
        }
    }
}
