package com.example.user_microservice.validation.user;

import com.example.user_microservice.exception.DataValidationException;
import com.example.user_microservice.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserValidation {

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
