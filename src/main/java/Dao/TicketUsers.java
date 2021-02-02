package Dao;

import models.Tickets;
import models.User;

import java.util.List;

public interface TicketUsers {
    void addTicketToUser(int userId, int ticketId);

    List<Tickets> getUsersTickets(int userId);

    List<User> getTicketUsers(int ticketId);

    void deleteUserFromGroup(int userId, int ticketId);
}
