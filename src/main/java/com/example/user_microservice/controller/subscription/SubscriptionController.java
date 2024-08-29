package com.example.user_microservice.controller.subscription;

import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.dto.user.UserFilterDto;
import com.example.user_microservice.manager.subscription.SubscriptionManager;
import com.example.user_microservice.utils.Paths;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.SUBSCRIPTION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionController {

    SubscriptionManager subscriptionManager;

    @PostMapping("/{followerId}/user/{authorId}")
    public void followUser(
            @PathVariable("followerId") Long followerId,
            @PathVariable("authorId") Long authorId
    ) {
        subscriptionManager.followUser(followerId,  authorId);
    }

    @DeleteMapping("/{followerId}/user/{authorId}")
    public void unfollowUser(
            @PathVariable("followerId") Long followerId,
            @PathVariable("authorId") Long authorId
    ) {
        subscriptionManager.unfollowUser(followerId, authorId);
    }

    @GetMapping("/{authorId}/followers")
    public List<UserDto> getFollowers(
            @PathVariable("authorId") Long authorId,
            @RequestBody UserFilterDto filterDto
    ) {
        return subscriptionManager.getFollowers(authorId, filterDto);
    }

    @GetMapping("/{authorId}/followers/count")
    public Long getFollowersCount(@PathVariable("authorId") Long authorId) {
        return subscriptionManager.getFollowersCount(authorId);
    }

    @GetMapping("/{followerId}/authors")
    public List<UserDto> getAuthors(
            @PathVariable("followerId") Long authorId,
            @RequestBody UserFilterDto filterDto
    ) {
        return subscriptionManager.getAuthors(authorId, filterDto);
    }

    @GetMapping("/{followerId}/authors/count")
    public Long getAuthorsCount(@PathVariable("followerId") Long followerId) {
        return subscriptionManager.getAuthorsCount(followerId);
    }
}
