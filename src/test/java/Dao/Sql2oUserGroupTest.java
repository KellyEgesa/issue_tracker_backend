package Dao;

import models.Group;
import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oUserGroupTest {

    private static Sql2oUser userDao;
    private static Sql2oGroup groupDao;
    private static Sql2oUserGroup userGroupDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        userDao = new Sql2oUser();
        userGroupDao = new Sql2oUserGroup();
        groupDao = new Sql2oGroup();
    }

    User user = new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
    User secondUser = new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
    Group group = new Group("Microsoft", user.getUserId());
    Group secondGroup = new Group("Moringa", user.getUserId());

    private void setUpGroup() {
        userDao.saveNewUser(user);
        userDao.saveNewUser(secondUser);
        groupDao.createNewGroup(group);
        groupDao.createNewGroup(secondGroup);
    }

    @Test
    public void addUserToGroupAddsCorrectly() {
        setUpGroup();
        userGroupDao.addUserToGroup(user.getUserId(), group.getGroupId());
        assertEquals(1, userGroupDao.getUserGroupByGroupId(group.getGroupId()).size());
    }

    @Test
    public void getUsersGroup() {
        setUpGroup();
        userGroupDao.addUserToGroup(user.getUserId(), group.getGroupId());
        userGroupDao.addUserToGroup(user.getUserId(), secondGroup.getGroupId());
        assertEquals(2, userGroupDao.getUsersGroup(user.getUserId()).size());
    }

    @Test
    public void getGroupUsers() {
        setUpGroup();
        userGroupDao.addUserToGroup(user.getUserId(), group.getGroupId());
        userGroupDao.addUserToGroup(secondUser.getUserId(), group.getGroupId());
        assertEquals(2, userGroupDao.getGroupUsers(group.getGroupId()).size());
    }

    @Test
    public void deleteUserFromGroup() {
        setUpGroup();
        userGroupDao.addUserToGroup(user.getUserId(), group.getGroupId());
        userGroupDao.addUserToGroup(user.getUserId(), secondGroup.getGroupId());
        userGroupDao.deleteUserFromGroup(user.getUserId(), group.getGroupId());
        assertEquals("Moringa", userGroupDao.getUsersGroup(user.getUserId()).get(0).getGroupName());
    }
}