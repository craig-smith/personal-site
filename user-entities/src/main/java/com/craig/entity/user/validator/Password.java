package com.craig.entity.user.validator;

import javax.validation.Constraint;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;

/**
 * Created by Craig on 3/12/2016.
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@SupportedValidationTarget({ValidationTarget.PARAMETERS})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Password {

    String message() default "Password must contain at least 8 characters, one digit, a lower case letter, an upper case letter, and a special character.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
