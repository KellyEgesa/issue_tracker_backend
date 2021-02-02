package models;

import java.sql.Timestamp;
import java.util.Objects;

public class Tickets {
    private String ticketName;
    private int ticketId;
    private int ticketProjectId;
    private String ticketDescription;
    private int ticketStatus;
    private int ticketPriority;
    private Timestamp ticketDueDate;

    public Tickets(String ticketName,
                   String ticketDescription,
                   int ticketStatus,
                   int ticketPriority,
                   Timestamp ticketDueDate, int ticketProjectId) {
        this.ticketProjectId = ticketProjectId;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.ticketDueDate = ticketDueDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public int getTicketPriority() {
        return ticketPriority;
    }

    public Timestamp getTicketDueDate() {
        return ticketDueDate;
    }

    public int getTicketProjectId() {
        return ticketProjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tickets)) return false;
        Tickets tickets = (Tickets) o;
        return getTicketId() == tickets.getTicketId() &&
                getTicketProjectId() == tickets.getTicketProjectId() &&
                Objects.equals(getTicketName(), tickets.getTicketName()) &&
                Objects.equals(getTicketDescription(), tickets.getTicketDescription()) &&
                Objects.equals(getTicketStatus(), tickets.getTicketStatus()) &&
                Objects.equals(getTicketPriority(), tickets.getTicketPriority()) &&
                Objects.equals(getTicketDueDate(), tickets.getTicketDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketName(), getTicketId(), getTicketProjectId(), getTicketDescription(), getTicketStatus(), getTicketPriority(), getTicketDueDate());
    }
}
