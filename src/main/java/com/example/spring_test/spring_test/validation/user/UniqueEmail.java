package com.example.spring_test.spring_test.validation.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ElementType.TYPE: Indicates that the annotation can be applied to a class, interface, or enum.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email already Exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
