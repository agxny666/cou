package com.ustyn.courseproject.service.ticket;

import com.ustyn.courseproject.document.Ticket;
import com.ustyn.courseproject.document.reader.Reader;

public interface TicketService {
    Ticket save(Ticket ticket);
    Ticket findById(String id);
    void deleteById(String id);
}
