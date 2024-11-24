package com.ustyn.courseproject.service.ticket;

import com.ustyn.courseproject.document.Ticket;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket findById(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        ticketRepository.deleteById(id);
    }
}
