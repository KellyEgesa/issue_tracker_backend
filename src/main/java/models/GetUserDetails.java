package models;

import java.util.List;

public class GetUserDetails {
    private User user;
    private List<Group> groups;

    public GetUserDetails(User user, List<Group> groups) {
        this.user = user;
        this.groups = groups;
    }

    public User getUserDetails() {
        return user;
    }

    public List<Group> getGroupsUserDetails() {
        return groups;
    }
}
