package com.ustyn.courseproject.validation.inPast;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class InPastConstraintValidator implements ConstraintValidator<InPast, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        return date.isBefore(LocalDate.now().plusDays(1));// today also counts
    }
}
