package com.example.spring_test.spring_test;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_test.spring_test.entity.User;
import com.example.spring_test.spring_test.mapper.UserMapper;
import com.example.spring_test.spring_test.service.UserService;

import static org.assertj.core.api.Assertions.*;

/**
 * Need to used an embedded database like H2
 */
@SpringBootTest
// @AutoConfigureTestDatabase
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    void insertUserService_validUser(){
        // given
        User user = new User("username1", "name1", "email1@test.com", "password1", "note1");

        // when
        String result = userService.insertUserService(user);

        // then
        User insertedUser = userMapper.findByUsername(user.getUsername());
        // verify(userMapper).insertUser(user);
        assertThat(insertedUser).isNotNull();
        assertThat(result).isEqualTo("Account added: " + user.getEmail());
    }
    
}
