package com.example.user_microservice.validation.user;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserValidation {

    UserService userService;

    public void checkEmail(String email) {
        if (userService.existsByEmail(email)) {
            throw new DataValidationException("Пользователь с таким email уже существует");
        }
    }

    public void checkUserInList(User user, List<User> users) {
        if (Objects.nonNull(users) && users.contains(user)) {
            throw new DataValidationException("Пользователь уже находиться в списке");
        }
    }

    public void checkUserNotInList(User user, List<User> users) {
        if (Objects.nonNull(users) && !users.contains(user)) {
            throw new DataValidationException("Пользователь не находится в списке");
        }
    }
}
