package com.ustyn.courseproject.validation.inPast;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InPastConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InPast {
    String message() default "повинно бути у минулому";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
