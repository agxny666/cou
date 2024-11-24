package com.ustyn.courseproject.constants;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
public enum Roles {
    ROLE_GUEST("ROLE_GUEST"),
    ROLE_OPERATOR("ROLE_OPERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public static List<String> asList() {
        return Stream.of(Roles.values())
                .map(Roles::getValue)
                .toList();
    }

}
