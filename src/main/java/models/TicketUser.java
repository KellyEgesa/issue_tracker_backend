package models;

public class TicketUser {
    private int userId;
    private int ticketId;

    public TicketUser(int userId, int ticketId){
        this.userId = userId;
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTicketId() {
        return ticketId;
    }
}
