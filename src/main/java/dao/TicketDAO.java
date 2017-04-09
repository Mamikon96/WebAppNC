package dao;

import models.Ticket;

import java.util.List;

public interface TicketDAO {
    boolean addTicket(Ticket ticket);
    boolean updateTicket(Ticket ticket);
    boolean deleteTicket(Ticket ticket);
    Ticket getTicketById(long id);
    List<Ticket> getTicketsByBuyer(String buyer);
    List<Ticket> getAllTickets();
}
