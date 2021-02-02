package Dao;

import models.TicketUser;
import models.Tickets;
import models.User;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Sql2oTicketUsers implements TicketUsers {

    private static Sql2oTickets ticketsDao = new Sql2oTickets();
    private static Sql2oUser userDao  = new Sql2oUser();

    @Override
    public void addTicketToUser(int userId, int ticketId) {
        String sql = "INSERT INTO ticketuser (userId ,ticketId) VALUES (:userId ,:ticketId)";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql, true)
                    .addParameter("userId", userId)
                    .addParameter("ticketId", ticketId)
                    .executeUpdate();
        }
    }

    public List<TicketUser> getTicketUserByUserId(int userId) {
        String sql = "SELECT * FROM ticketuser WHERE userId = :userId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("userId", userId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(TicketUser.class);
        }
    }

    public List<TicketUser> getTicketUserByTicketId(int ticketId) {
        String sql = "SELECT * FROM ticketuser WHERE ticketId = :ticketId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("ticketId", ticketId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(TicketUser.class);
        }
    }

    @Override
    public List<Tickets> getUsersTickets(int userId) {
        List<Tickets> usersTickets = new ArrayList<>();
        List<TicketUser> valueReturned = getTicketUserByUserId(userId);
        for (TicketUser ticketUser : valueReturned) {
            int ticketId = ticketUser.getTicketId();
            usersTickets.add(ticketsDao.getTicketById(ticketId));
        }
        return usersTickets;
    }

    @Override
    public List<User> getTicketUsers(int ticketId) {
        List<User> TicketUsers = new ArrayList<>();
        List<TicketUser> valueReturned = getTicketUserByTicketId(ticketId);
        for (TicketUser ticketUser : valueReturned) {
            int userId = ticketUser.getUserId();
            TicketUsers.add(userDao.getUserById(userId));
        }
        return TicketUsers;
    }

    @Override
    public void deleteUserFromGroup(int userId, int ticketId) {
        String sql = "DELETE FROM ticketuser WHERE userId = :userId AND ticketId = :ticketId";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("ticketId", ticketId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
