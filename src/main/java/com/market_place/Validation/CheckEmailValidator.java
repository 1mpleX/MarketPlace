package com.market_place.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {
    private String[] allowedDomains;

    @Override
    public void initialize(CheckEmail constraintAnnotation) {
        this.allowedDomains = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Arrays.stream(allowedDomains).anyMatch(email::endsWith);
    }
}
