package com.ustyn.courseproject.config.passwordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString(); // No encoding
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Simply compare raw password with the stored value
        return rawPassword.toString().equals(encodedPassword);
    }
}