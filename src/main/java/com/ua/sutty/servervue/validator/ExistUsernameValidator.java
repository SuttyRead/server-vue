package com.ua.sutty.servervue.validator;

import com.ua.sutty.servervue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistUsernameValidator implements ConstraintValidator<ExistUsername, String> {

    private final UserRepository userRepository;

    @Autowired
    public ExistUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(ExistUsername constraint) {
        System.out.println("Contraint = " + constraint);
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        System.out.println(username);
        System.out.println(userRepository);
        boolean b = this.userRepository.existsByUsername(username);
        System.out.println("Username = " + b);
        return !b;
    }
}
