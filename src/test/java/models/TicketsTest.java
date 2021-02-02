package models;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class TicketsTest {

    Timestamp rightNow = new Timestamp(new Date().getTime());

    private Tickets setUpTickets() {
        return new Tickets("Customer Complaint", "Bad food", 0, 1, rightNow, 1);
    }

    @Test
    public void ticketsInstantiatesCorrectly() {
        Tickets testTickets = setUpTickets();
        assertTrue(testTickets instanceof Tickets);
    }

    @Test
    public void setTicketIdSetsCorrectly() {
        Tickets testTickets = setUpTickets();
        testTickets.setTicketId(1);
        assertEquals(1, testTickets.getTicketId());
    }

    @Test
    public void gettersWorkWell() {
        Tickets testTickets = setUpTickets();
        assertEquals("Customer Complaint", testTickets.getTicketName());
        assertEquals("Bad food", testTickets.getTicketDescription());
        assertEquals(0, testTickets.getTicketStatus());
        assertEquals(1, testTickets.getTicketPriority());
        assertEquals(rightNow.getDay(), testTickets.getTicketDueDate().getDate());
        assertEquals(1, testTickets.getTicketProjectId());
    }

    @Test
    public void equalsWorksCorrectly() {
        Tickets testTickets = setUpTickets();
        Tickets anotherTestTickets = new Tickets("Customer Complaint", "Bad food", 0, 1, rightNow, 1);
        assertTrue(testTickets.equals(anotherTestTickets));
    }
}