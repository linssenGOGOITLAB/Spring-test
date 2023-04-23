package com.example.spring_test.spring_test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_test.spring_test.entity.User;
import com.example.spring_test.spring_test.service.UserService;

import jakarta.validation.Valid;


@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add-user-controller")
    public ResponseEntity<String> addUserController(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {

            String errorMessage = result.getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .findFirst().orElse("Invalid user data");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        userService.insertUserController(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/add-user-service")
    public ResponseEntity<Object> addUserService(@RequestBody User user) {
    
       try {
            String message = userService.insertUserService(user);
            return ResponseEntity.ok().body(Map.of("message", message));
       }
       catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
       }
       
    }
    
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    
}
