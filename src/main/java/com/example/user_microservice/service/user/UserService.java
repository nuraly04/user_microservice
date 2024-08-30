package com.example.user_microservice.service.user;

import com.example.user_microservice.dto.user.UserFilterDto;
import com.example.user_microservice.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User get(Long userId);

    User save(User user);

    void followToAuthor(User follower, User author);

    void unfollowToAuthor(User follower, User author);

    void deleteMentorshipUsers(List<User> mentees, User mentor);

    boolean existsByFollowerAndAuthor(User follower, User author);

    Long countFollowers(User author);

    Long countAuthors(User follower);

    List<User> findMenteesByMentorId(Long mentorId);

    List<User> getFollowersByAuthor(User author, UserFilterDto filterDto);

    List<User> getAuthorsByFollower(User follower, UserFilterDto filterDto);

    List<User> findUsersByIds(List<Long> userIds);

    List<User> findMentorsByMenteeId(Long menteeId);

    List<User> findMemberByEventId(Long eventId);

    Long countMembersByEventId(Long eventId);
}
