package com.ustyn.courseproject.document.user;

import com.ustyn.courseproject.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field(value = "username")
    private String username;

    @DBRef
    @Field(value = "password")
    private Key password;

    @Field(value = "enabled")
    private boolean enabled = true;

    @DBRef
    @Field(value = "roles")
    private List<Role> roles;

    public User(String username, Key password, boolean enabled, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = new Key(userDto.getPasswordId(), userDto.getPassword());
        this.enabled = userDto.isEnabled();
        this.roles = List.of(new Role(userDto.getRoleId(), userDto.getRole()));
    }
}

