package com.scott.userservice.exception;

import com.scott.userservice.model.Person;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException {
    Set<ConstraintViolation<Person>> violations;
    public ValidationException (Set<ConstraintViolation<Person>> violations) {
        this.violations = violations;

    }


}