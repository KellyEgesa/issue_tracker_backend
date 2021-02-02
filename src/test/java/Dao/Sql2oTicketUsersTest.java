package Dao;

import models.Tickets;
import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;


import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oTicketUsersTest {

    private static Sql2oTickets ticketsDao;
    private static Sql2oUser userDao;
    private static Sql2oTicketUsers ticketUsersDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        userDao = new Sql2oUser();
        ticketsDao = new Sql2oTickets();
        ticketUsersDao = new Sql2oTicketUsers();
    }

    Timestamp rightNow = new Timestamp(new Date().getTime());
    User user = new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
    User secondUser = new User("Kelly", "fBAda12432wre", "kelly.egesa@gmail.com");
    Tickets ticket = new Tickets("Customer Complaint", "Bad food", 0, 1, rightNow, 1);
    Tickets secondTicket = new Tickets("Complaint", "Bad food", 0, 1, rightNow, 1);

    private void setUpTickets() {
        userDao.saveNewUser(user);
        userDao.saveNewUser(secondUser);
        ticketsDao.saveNewTicket(ticket);
        ticketsDao.saveNewTicket(secondTicket);
    }

    @Test
    public void addTicketToUser() {
        setUpTickets();
        ticketUsersDao.addTicketToUser(user.getUserId(), ticket.getTicketId());
        assertEquals(1, ticketUsersDao.getTicketUserByUserId(user.getUserId()).size());
    }

    @Test
    public void getUsersTickets() {
        setUpTickets();
        ticketUsersDao.addTicketToUser(user.getUserId(), ticket.getTicketId());
        ticketUsersDao.addTicketToUser(user.getUserId(), secondTicket.getTicketId());
        assertEquals("Customer Complaint", ticketUsersDao.getUsersTickets(user.getUserId()).get(0).getTicketName());
    }

    @Test
    public void getTicketUsers() {
        setUpTickets();
        ticketUsersDao.addTicketToUser(user.getUserId(), ticket.getTicketId());
        ticketUsersDao.addTicketToUser(secondUser.getUserId(), ticket.getTicketId());
        assertEquals("KellyEgesa", ticketUsersDao.getTicketUsers(ticket.getTicketId()).get(0).getUsername());
    }

    @Test
    public void deleteUserFromGroup() {
        setUpTickets();
        ticketUsersDao.addTicketToUser(user.getUserId(), ticket.getTicketId());
        ticketUsersDao.addTicketToUser(secondUser.getUserId(), ticket.getTicketId());
        ticketUsersDao.deleteUserFromGroup(user.getUserId(),ticket.getTicketId());
        assertEquals("Kelly", ticketUsersDao.getTicketUsers(ticket.getTicketId()).get(0).getUsername());

    }
}