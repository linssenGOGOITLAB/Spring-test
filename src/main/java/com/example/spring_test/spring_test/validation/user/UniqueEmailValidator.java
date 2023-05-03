package com.example.spring_test.spring_test.validation.user;


import com.example.spring_test.spring_test.mapper.UserMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    private final UserMapper userMapper;

    public UniqueEmailValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // Add a no-args constructor
    public UniqueEmailValidator() {
        this.userMapper = null;
    }

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return !userMapper.isEmailExist(email);
    }
    
}
