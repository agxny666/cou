package com.ustyn.courseproject.validation.unique;

import com.ustyn.courseproject.document.user.User;
import com.ustyn.courseproject.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, String> {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User existing = userService.findByUsername(username);

        return existing == null;
    }
}
