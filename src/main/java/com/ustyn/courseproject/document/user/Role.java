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
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    @Field(value = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
