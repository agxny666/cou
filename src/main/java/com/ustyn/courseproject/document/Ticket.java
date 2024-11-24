package com.ustyn.courseproject.document;

import com.ustyn.courseproject.document.literature.Literature;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "tickets")
public class Ticket {

    @Id
    private String id;

    @DBRef
    private List<Literature> borrowedLiteratures = new ArrayList<>();

    public Ticket(List<Literature> borrowedLiteratures) {
        this.borrowedLiteratures = borrowedLiteratures;
    }
}
