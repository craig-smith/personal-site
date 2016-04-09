package com.craig.entity.user.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Craig on 3/12/2016.
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String PASS_VALIDATOR = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private Matcher matcher;
    private Pattern pattern;
    public void initialize(Password password) {
        pattern = pattern.compile(PASS_VALIDATOR);
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
