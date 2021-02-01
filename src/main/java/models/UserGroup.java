package models;

public class UserGroup {
    private int userId;
    private int groupId;

    public UserGroup(int userId, int groupId){
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }
}
