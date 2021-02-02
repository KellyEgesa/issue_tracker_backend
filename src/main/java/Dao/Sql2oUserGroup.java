package Dao;

import models.Group;
import models.User;
import models.UserGroup;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserGroup implements UserGroupDao {
    private static Sql2oUser userDao = new Sql2oUser();
    private static Sql2oGroup groupDao = new Sql2oGroup();

    @Override
    public void addUserToGroup(int userId, int groupId) {
        String sql = "INSERT INTO usergroup (userId ,groupId) VALUES (:userId ,:groupId)";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql, true)
                    .addParameter("userId", userId)
                    .addParameter("groupId", groupId)
                    .executeUpdate();
        }
    }

    public List<UserGroup> getUserGroupByUserId(int userId) {
        String sql = "SELECT * FROM usergroup WHERE userId = :userId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("userId", userId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(UserGroup.class);
        }
    }

    public List<UserGroup> getUserGroupByGroupId(int groupId) {
        String sql = "SELECT * FROM usergroup WHERE groupId = :groupId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("groupId", groupId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(UserGroup.class);
        }
    }

    @Override
    public List<Group> getUsersGroup(int userId) {
        List<Group> usersGroup = new ArrayList<>();
        List<UserGroup> valueReturned = getUserGroupByUserId(userId);
        for (UserGroup userGroup : valueReturned) {
            int groupId = userGroup.getGroupId();
            usersGroup.add(groupDao.getGroupById(groupId));
        }
        return usersGroup;
    }

    @Override
    public List<User> getGroupUsers(int groupId) {
        List<User> groupUsers = new ArrayList<>();
        List<UserGroup> valueReturned = getUserGroupByGroupId(groupId);
        for (UserGroup userGroup : valueReturned) {
            int userId = userGroup.getUserId();
            groupUsers.add(userDao.getUserById(userId));
        }
        return groupUsers;
    }

    @Override
    public void deleteUserFromGroup(int userId, int groupId) {
        String sql = "DELETE FROM usergroup WHERE userId = :userId AND groupId = :groupId";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("groupId", groupId)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
