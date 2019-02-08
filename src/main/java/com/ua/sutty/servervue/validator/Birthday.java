package com.ua.sutty.servervue.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidator.class)
@Documented
public @interface Birthday {

    String message() default "Birthday incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
