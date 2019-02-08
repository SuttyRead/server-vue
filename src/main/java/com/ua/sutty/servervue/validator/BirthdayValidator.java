package com.ua.sutty.servervue.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class BirthdayValidator implements ConstraintValidator<Birthday, LocalDate> {
    public void initialize(Birthday constraint) {
    }

    public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
        return LocalDate.now().isAfter(birthday);
    }
}

