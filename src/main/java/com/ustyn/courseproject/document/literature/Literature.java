package com.ustyn.courseproject.document.literature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "literatures")
public abstract class Literature {

    @Id
    private ObjectId id;
    private String title;
    private String author;
    private String inventoryNumber;
    private boolean available;


    public Literature(String title, String author, String inventoryNumber, boolean available) {
        this.title = title;
        this.author = author;
        this.inventoryNumber = inventoryNumber;
        this.available = available;
    }
}
