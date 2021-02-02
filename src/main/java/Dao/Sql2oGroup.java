package Dao;

import models.Group;
import org.sql2o.Connection;

public class Sql2oGroup implements GroupDao {
    @Override
    public void createNewGroup(Group newGroup) {
        String sql = "INSERT INTO users (groupName ,userAdminId) VALUES (:groupName ,:userAdminId)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("groupName", newGroup.getGroupName())
                    .addParameter("userAdminId", newGroup.getUserAdminId())
                    .executeUpdate()
                    .getKey();
            newGroup.setGroupId(id);
        }
    }

    @Override
    public Group getGroupById(int id) {
        String sql = "SELECT * FROM users WHERE groupId = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Group.class);
        }
    }
}
