package com.hotelreservation.lib.Validator.impl;

import com.hotelreservation.lib.Validator.annotations.UniqueUsername;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    private final UserRepository userRepository;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByUsername(s);

        return user == null;
    }
}
