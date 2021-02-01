package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getFireBaseUserId(), user.getFireBaseUserId()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getUserId(), getFireBaseUserId(), getEmail());
    }
}
