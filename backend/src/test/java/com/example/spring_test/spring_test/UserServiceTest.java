package com.example.spring_test.spring_test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.example.spring_test.spring_test.entity.User;
import com.example.spring_test.spring_test.mapper.UserMapper;
import com.example.spring_test.spring_test.service.UserService;
import com.example.spring_test.spring_test.validation.user.UniqueEmailValidator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    private BCryptPasswordEncoder passwordEncoder;
    private Validator validator;
    private UserService userService;

    

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
      
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        passwordEncoder = new BCryptPasswordEncoder();
        
        userService = new UserService(userMapper, passwordEncoder, validator);
    }


    @Test
    void getAllUsers(){
        // given
       List<User> users = new ArrayList<>();
       users.add(new User("username1", "name1", "email1@test.com", "password1", "note1"));
       users.add(new User("username2", "name2", "email2@test.com", "password2", "note2"));
       when(userMapper.getAllUsers()).thenReturn(users);

       // when
       List<User> result = userService.getAllUsers();

       // then
       verify(userMapper).getAllUsers();
       assertThat(result).isEqualTo(users);
    }

    @Test
    void getUserById(){
        // given
        User user = new User("username1", "name1", "email1@test.com", "password1", "note1");
        user.setId(1L);
        when(userMapper.getUserById(1L)).thenReturn(user);

        // when
        User result = userService.getUserById(1L);

        //then
        verify(userMapper).getUserById(1L);
        assertThat(result).isEqualTo(user);
    }

    // @Test
    // void insertUserService_validUser(){
    //     // given
    //     User user = new User("username1", "name1", "email1@test.com", "password1", "note1");
    //     when(userMapper.isEmailExist(user.getEmail())).thenReturn(true);
    //     //when
    //     String result = userService.insertUserService(user);

    //     //then
    //     verify(userMapper).insertUser(user);
    //     assertThat(result).isEqualTo("Account added: " + user.getEmail());
    // }
     

    @Test
    void insertUserService_validUser(){
       // given
       User user = new User("username1", "name1", "email1@test.com", "password1", "note1");
       UniqueEmailValidator validator = new UniqueEmailValidator(userMapper); // Inject the mocked UserMapper
       validator.initialize(null); // Initialize the validator
   
       // when
       boolean isValid = validator.isValid(user.getEmail(), null);
   
       // then
       assertThat(isValid).isTrue();
    }
     
    
    
}
