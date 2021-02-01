package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TicketUserTest {

    private TicketUser setUpTicketUser() {
        return new TicketUser(13, 15);
    }

    @Test
    public void ticketUserInstantiatesProperly() {
        TicketUser ticketUser = setUpTicketUser();
        assertTrue(ticketUser instanceof TicketUser);

    }

    @Test
    public void getUserId() {
        TicketUser ticketUser = setUpTicketUser();
        assertEquals(13, ticketUser.getUserId());
    }

    @Test
    public void getTicketId() {
        TicketUser ticketUser = setUpTicketUser();
        assertEquals(15, ticketUser.getTicketId());
    }
}