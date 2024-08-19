package com.example.user_microservice.controller.user;

import com.example.user_microservice.utils.Paths;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Paths.USER)
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

}
