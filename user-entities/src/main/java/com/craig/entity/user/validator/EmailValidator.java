package com.craig.entity.user.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Created by Craig on 3/12/2016.
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    public void initialize(Email email) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        org.apache.commons.validator.routines.EmailValidator validator = org.apache.commons.validator.routines.EmailValidator.getInstance();
        return validator.isValid(s);
    }
}
