package Dao;

import models.Group;
import models.User;

import java.util.List;

public interface UserGroupDao {
    void addUserToGroup(int userId, int groupId);
    List<Group> getUsersGroup(int userId);
    List<User> getGroupUsers(int userId);
    void deleteUserFromGroup(int userId, int groupId);
}
