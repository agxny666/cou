package com.ustyn.courseproject.document.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "keys")
public class Key {

    @Id
    private String id;

    @Field(value = "password")
    private String password;

    public Key(String password) {
        this.password = password;
    }
}
