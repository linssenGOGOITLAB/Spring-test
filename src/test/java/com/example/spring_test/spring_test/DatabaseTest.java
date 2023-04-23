package com.example.spring_test.spring_test;



import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.spring_test.spring_test.entity.User;


@SpringBootTest
public class DatabaseTest {
    
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println("Display Users");
        System.out.println(users);
    }
}
