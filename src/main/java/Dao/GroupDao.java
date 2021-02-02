package Dao;

import models.Group;

public interface GroupDao {
    void createNewGroup(Group newGroup);

    Group getGroupById(int id);
}
