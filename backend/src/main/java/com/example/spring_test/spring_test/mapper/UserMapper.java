package com.example.spring_test.spring_test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.spring_test.spring_test.entity.User;


@Mapper
public interface UserMapper{
    @Select("select id, name, email, username where username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT id, username, name, email, password, note FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(Long id);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean isEmailExist(@Param("email") String email);

    @Insert("INSERT INTO users(name, username, password, email, note) VALUES(#{name}, #{username}, #{password}, #{email}, #{note})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE user SET name = #{name}, username = #{username}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);
}
