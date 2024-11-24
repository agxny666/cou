package com.ustyn.courseproject.config.passwordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainTextPasswordEncoder(); // Or any other encoder you need
    }
}
