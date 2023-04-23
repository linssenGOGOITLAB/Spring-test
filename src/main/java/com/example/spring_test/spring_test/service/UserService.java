package com.example.spring_test.spring_test.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_test.spring_test.entity.User;
import com.example.spring_test.spring_test.mapper.UserMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Validator validator;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, Validator validator) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public void insertUserController(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.insertUser(user);
    }

    public String insertUserService(@Valid User user) {
        // Validation
        validateUser(user);

        // Endcode Password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Insert User
        userMapper.insertUser(user);

        return "Account added: " + user.getEmail();
    }

    private void validateUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            String errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Validation error: " + errorMessages);
        }
        // Set<ConstraintViolation<User>> violations = validator.validate(user);
        // if (!violations.isEmpty()) {
        //     StringBuilder sb = new StringBuilder();
        //     for (ConstraintViolation<User> constraintViolation : violations) {
        //         sb.append(constraintViolation.getMessage());
        //     }
        //     throw new ConstraintViolationException("Error occured: " + sb.toString(), violations);
        // }
    }



    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
    
}
