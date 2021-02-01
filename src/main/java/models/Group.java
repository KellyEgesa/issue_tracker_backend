package models;

import java.util.Objects;

public class Group {
    private int groupId;
    private String groupName;
    private int userAdminId;

    public Group(String groupName, int userAdminId) {
        this.groupName = groupName;
        this.userAdminId = userAdminId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getUserAdminId() {
        return userAdminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getGroupId() == group.getGroupId() &&
                getUserAdminId() == group.getUserAdminId() &&
                Objects.equals(getGroupName(), group.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getGroupName(), getUserAdminId());
    }
}
