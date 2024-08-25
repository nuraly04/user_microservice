package com.example.user_microservice.service.user.impl;

import com.example.user_microservice.exception.DataNotFoundException;
import com.example.user_microservice.model.user.User;
import com.example.user_microservice.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private Long userId;
    private User user;

    @BeforeEach
    public void setUp() {
        userId = 1L;
        user = new User();
        user.setId(1L);
    }

    @Test
    public void findById() {

        userService.findById(userId);

        verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void getPositiveTest() {

        when(userService.findById(userId)).thenReturn(Optional.ofNullable(user));

        userService.get(userId);

        verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void getNegativeTest() {

        assertThrows(DataNotFoundException.class, () -> userService.get(userId));
    }
}
