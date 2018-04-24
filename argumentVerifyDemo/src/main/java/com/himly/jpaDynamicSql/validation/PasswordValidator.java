package com.himly.jpaDynamicSql.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordValidator implements ConstraintValidator<NotNull, String> {

    private static final String[] ILLEGAL_PASSWORD_LIST = {
            "123456",
            "null"
    };

    @Override
    public void initialize(NotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            throw new RuntimeException();
        }

        return !Arrays.asList(ILLEGAL_PASSWORD_LIST).contains(value);
    }
}
