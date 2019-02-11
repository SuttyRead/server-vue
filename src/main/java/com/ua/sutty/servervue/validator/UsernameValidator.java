package com.ua.sutty.servervue.validator;

import com.ua.sutty.servervue.model.User;
import com.ua.sutty.servervue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsernameValidator implements Validator {


    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userRepository.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "UsernameAlreadyExists", "This login already registered");
        }
    }

}
