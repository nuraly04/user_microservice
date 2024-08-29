package com.example.user_microservice.validation.subscription;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionValidation {

    UserService userService;

    public void checkFollowerAndAuthorToFollow(User follower, User author) {
        if (follower.equals(author)) {
            throw new DataValidationException("Пользователь не может подписаться на самого себя");
        }
        if (userService.existsByFollowerAndAuthor(follower, author)) {
            throw new DataValidationException(String.format("Пользователь %s уже является подписчиком - %s", follower, author));
        }
    }

    public void checkFollowerAndAuthorToUnfollow(User follower, User author) {
        if (!userService.existsByFollowerAndAuthor(follower, author)) {
            throw new DataValidationException("Ползователь ввел невалидные данные");
        }
    }
}
