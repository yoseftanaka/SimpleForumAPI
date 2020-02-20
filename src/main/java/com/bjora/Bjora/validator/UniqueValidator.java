package com.bjora.Bjora.validator;

import com.bjora.Bjora.entities.User;
import com.bjora.Bjora.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByEmail(s);
        return user != null;
    }
}
