package com.example.user_microservice.manager.subscription;

import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.dto.user.UserFilterDto;

import java.util.List;

public interface SubscriptionManager {

    List<UserDto> getFollowers(Long authorId, UserFilterDto filterDto);

    List<UserDto> getAuthors(Long followerId, UserFilterDto filterDto);

    Long getFollowersCount(Long authorId);

    Long getAuthorsCount(Long followerId);

    void followUser(Long followerId, Long authorId);

    void unfollowUser(Long followerId, Long authorId);
}
