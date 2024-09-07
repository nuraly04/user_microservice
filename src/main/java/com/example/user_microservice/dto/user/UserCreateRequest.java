package com.example.user_microservice.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String patronymic;

    @NotNull
    private LocalDate dateOfBirth;

    @Email
    private String email;

    private String phone;

    private Long cityId;

    private List<ContactDto> contacts;
}
