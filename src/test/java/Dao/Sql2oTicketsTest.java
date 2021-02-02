package Dao;

import models.Project;
import models.Tickets;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oTicketsTest {

    private static Sql2oProject projectDao;
    private static Sql2oTickets ticketsDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        ticketsDao = new Sql2oTickets();
        projectDao = new Sql2oProject();
    }


    Timestamp rightNow = new Timestamp(new Date().getTime());
    Project project = new Project("MicroFinance", "Saving small amount of money", rightNow);
    Project secondProject = new Project("Issue Tracker", "Tracking errors of the system", rightNow);

    private Tickets setUpTickets() {
        projectDao.saveNewProject(project);
        return new Tickets("Customer Complaint", "Bad food", 0, 1, rightNow, project.getProjectId());
    }

    private Tickets setUpSecondTickets() {
        projectDao.saveNewProject(secondProject);
        return new Tickets("Customer Complaint", "Bad food", 0, 1, rightNow, secondProject.getProjectId());
    }

    @Test
    public void saveNewTicketSetsIdCorrectly() {
        Tickets tickets = setUpTickets();
        int originalId = tickets.getTicketId();
        ticketsDao.saveNewTicket(tickets);
        assertNotEquals(originalId, tickets.getTicketProjectId());
    }

    @Test
    public void getTicketById() {
        Tickets tickets = setUpTickets();
        Tickets secondTickets = setUpSecondTickets();
        ticketsDao.saveNewTicket(tickets);
        ticketsDao.saveNewTicket(secondTickets);
        int originalId = tickets.getTicketId();
        assertEquals("Customer Complaint", ticketsDao.getTicketById(originalId).getTicketName());
    }

    @Test
    public void getTicketsByProjectId() {
        Tickets tickets = setUpTickets();
        Tickets secondTickets = setUpSecondTickets();
        ticketsDao.saveNewTicket(tickets);
        ticketsDao.saveNewTicket(secondTickets);
        int originalId = project.getProjectId();
        assertEquals("Customer Complaint", ticketsDao.getTicketsByProjectId(originalId).get(0).getTicketName());
    }

    @Test
    public void updateTicketStatus() {
        Tickets tickets = setUpTickets();
        Tickets secondTickets = setUpSecondTickets();
        ticketsDao.saveNewTicket(tickets);
        ticketsDao.saveNewTicket(secondTickets);
        int originalId = tickets.getTicketId();
        ticketsDao.updateTicketStatus(originalId, 2);
        assertEquals(2, ticketsDao.getTicketById(originalId).getTicketStatus());
    }

    @Test
    public void updateTicketPriority() {
        Tickets tickets = setUpTickets();
        Tickets secondTickets = setUpSecondTickets();
        ticketsDao.saveNewTicket(tickets);
        ticketsDao.saveNewTicket(secondTickets);
        int originalId = tickets.getTicketId();
        ticketsDao.updateTicketPriority(originalId, 2);
        assertEquals(2, ticketsDao.getTicketById(originalId).getTicketPriority());

    }
}