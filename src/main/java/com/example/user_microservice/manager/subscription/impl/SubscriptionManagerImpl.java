package com.example.user_microservice.manager.subscription.impl;

import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.dto.user.UserFilterDto;
import com.example.user_microservice.manager.subscription.SubscriptionManager;
import com.example.user_microservice.mapper.user.UserMapper;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.user.UserService;
import com.example.user_microservice.validation.subscription.SubscriptionValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionManagerImpl implements SubscriptionManager {

    UserService userService;
    UserMapper userMapper;
    SubscriptionValidation subscriptionValidation;

    @Override
    @Transactional
    public void followUser(Long followerId, Long authorId) {
        User follower = userService.get(followerId);
        User author = userService.get(authorId);
        subscriptionValidation.checkFollowerAndAuthorToFollow(follower, author);
        userService.followToAuthor(follower, author);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getFollowersCount(Long authorId) {
        User author = userService.get(authorId);
        return userService.countFollowers(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAuthorsCount(Long followerId) {
        User follower = userService.get(followerId);
        return userService.countAuthors(follower);
    }

    @Override
    @Transactional
    public void unfollowUser(Long followerId, Long authorId) {
        User follower = userService.get(followerId);
        User author = userService.get(authorId);
        subscriptionValidation.checkFollowerAndAuthorToUnfollow(follower, author);
        userService.unfollowToAuthor(follower, author);
    }

    @Override
    @Transactional
    public List<UserDto> getFollowers(Long authorId, UserFilterDto filterDto) {
        User author = userService.get(authorId);
        List<User> followers = userService.getFollowersByAuthor(author, filterDto);
        return followers.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<UserDto> getAuthors(Long followerId, UserFilterDto filterDto) {
        User follower = userService.get(followerId);
        List<User> followers = userService.getAuthorsByFollower(follower, filterDto);
        return followers.stream()
                .map(userMapper::toDto)
                .toList();
    }
}
