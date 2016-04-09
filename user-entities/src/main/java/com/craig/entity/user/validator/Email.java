package com.craig.entity.user.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by Craig on 3/12/2016.
 */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Email {

    String message() default "Email must be a valid email address.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
