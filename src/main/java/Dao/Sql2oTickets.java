package Dao;

import models.Tickets;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oTickets implements TicketsDao {
    @Override
    public void saveNewTicket(Tickets newTicket) {
        String sql = "INSERT INTO tickets (ticketName ,ticketDescription , ticketStatus,ticketPriority, ticketDueDate, ticketProjectId) VALUES " +
                "(:ticketName ,:ticketDescription , :ticketStatus,:ticketPriority, :ticketDueDate, :ticketProjectId) ";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("ticketName", newTicket.getTicketName())
                    .addParameter("ticketDescription", newTicket.getTicketDescription())
                    .addParameter("ticketStatus", newTicket.getTicketStatus())
                    .addParameter("ticketPriority", newTicket.getTicketPriority())
                    .addParameter("ticketDueDate", newTicket.getTicketDueDate())
                    .addParameter("ticketProjectId", newTicket.getTicketProjectId())
                    .executeUpdate()
                    .getKey();
            newTicket.setTicketId(id);
        }
    }

    @Override
    public Tickets getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE ticketId = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Tickets.class);
        }
    }

    @Override
    public List<Tickets> getTicketsByProjectId(int projectId) {
        String sql = "SELECT * FROM tickets WHERE ticketProjectId = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", projectId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Tickets.class);
        }
    }

    @Override
    public void updateTicketStatus(int id, int status) {
        String sql = "UPDATE tickets SET ticketStatus =:ticketStatus WHERE ticketId=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("ticketStatus", status)
                    .executeUpdate();
        }
    }

    @Override
    public void updateTicketPriority(int id, int priority) {
        String sql = "UPDATE tickets SET ticketPriority =:ticketPriority WHERE ticketId=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("ticketPriority", priority)
                    .executeUpdate();
        }
    }
}
