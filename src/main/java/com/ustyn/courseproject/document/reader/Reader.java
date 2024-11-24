package com.ustyn.courseproject.document.reader;

import com.ustyn.courseproject.document.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "readers")
public abstract class Reader {
    @Id
    private ObjectId id;

    private String name;

    private String address;

    private String ticketId;

    private LocalDate lastVisitDate;

    public Reader(String name, String address, Ticket ticket, LocalDate lastVisitDate) {
        this.name = name;
        this.address = address;
        this.ticketId = ticket.getId();
        this.lastVisitDate = lastVisitDate;
    }
}
