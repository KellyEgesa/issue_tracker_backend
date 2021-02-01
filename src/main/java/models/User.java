package models;

public class User {
    private String username;
    private int userId;
    private String fireBaseUserId;

    public User(String username, String fireBaseUserId){
        this.fireBaseUserId = fireBaseUserId;
        this.username = username;
    }
}
