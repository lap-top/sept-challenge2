package com.scott.accountservice.exception;

import com.scott.accountservice.model.Account;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException {
    Set<ConstraintViolation<Account>> violations;
    public ValidationException (Set<ConstraintViolation<Account>> violations) {
        this.violations = violations;

    }


}