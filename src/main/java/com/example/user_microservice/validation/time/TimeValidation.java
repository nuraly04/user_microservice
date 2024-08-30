package com.example.user_microservice.validation.time;

import com.example.user_microservice.exception.DataValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class TimeValidation {

    public void checkStartDate(LocalDateTime startDate) {
        if (Objects.nonNull(startDate) && startDate.isBefore(LocalDateTime.now())) {
            throw new DataValidationException("Не валидное время");
        }
    }

    public void checkEndDate(LocalDateTime endDate) {
        if (Objects.nonNull(endDate) && endDate.isBefore(LocalDateTime.now())) {
            throw new DataValidationException("Не валидное время");
        }
    }

    public void checkStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || Objects.equals(startDate, endDate))
            return;
        if (endDate.isBefore(startDate)) {
            throw new DataValidationException("Не валидное время");
        }
    }

}
