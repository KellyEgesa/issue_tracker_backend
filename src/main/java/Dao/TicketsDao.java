package Dao;


import models.Tickets;

import java.util.List;

public interface TicketsDao {
    void saveNewTicket(Tickets newTicket);
    Tickets getTicketById(int id);
    List<Tickets> getTicketsByProjectId(int projectId);
    void updateTicketStatus(int id, int status);
    void updateTicketPriority(int id, int priority);
}
