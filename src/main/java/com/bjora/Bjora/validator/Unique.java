package com.bjora.Bjora.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "Email has been taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
