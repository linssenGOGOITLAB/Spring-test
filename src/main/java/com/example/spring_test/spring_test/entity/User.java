package com.example.spring_test.spring_test.entity;

import com.example.spring_test.spring_test.validation.user.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class User {
    
    private Long id;
    @NotNull
    private String name;

    private String username;
    @Email
    @UniqueEmail
    private String email;
    private String password;
    private String note;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
    }

}
