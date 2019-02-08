package com.ua.sutty.servervue.validator;

import com.ua.sutty.servervue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistEmailValidator implements ConstraintValidator<ExistEmail, String> {

   private final UserRepository userRepository;

   @Autowired
   public ExistEmailValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void initialize(ExistEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      boolean b = this.userRepository.existsByEmail(email);
      System.out.println("Email " + b);
      return !b;
   }
}
