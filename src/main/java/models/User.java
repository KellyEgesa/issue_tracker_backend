package models;

public class User {
    private String username;
    private int userId;
    private String fireBaseUserId;
    private String email;

    public User(String username, String fireBaseUserId, String email){
        this.fireBaseUserId = fireBaseUserId;
        this.username = username;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFireBaseUserId() {
        return fireBaseUserId;
    }

    public String getEmail() {
        return email;
    }
}
