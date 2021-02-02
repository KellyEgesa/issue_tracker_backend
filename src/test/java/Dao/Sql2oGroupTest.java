package Dao;

import models.Group;
import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oGroupTest {

    private static Sql2oUser userDao;
    private static Sql2oGroup groupDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        userDao = new Sql2oUser();
        groupDao = new Sql2oGroup();
    }

    private Group setUpGroup() {
        User user =  new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
        userDao.saveNewUser(user);
        return new Group("Microsoft", user.getUserId());
    }

    private Group setUpSecondGroup() {
        User user =  new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
        userDao.saveNewUser(user);
        return new Group("Moringa", user.getUserId());
    }

    @Test
    public void createNewGroupSetsIdCorrectly() {
        Group group = setUpGroup();
        int originalId = group.getGroupId();
        groupDao.createNewGroup(group);
        assertNotEquals(originalId, group.getGroupId());
    }

    @Test
    public void getGroupById() {
        Group group = setUpGroup();
        Group anotherGroup = setUpSecondGroup();
        groupDao.createNewGroup(group);
        int originalId = group.getGroupId();
        groupDao.createNewGroup(anotherGroup);
        assertEquals("Microsoft", groupDao.getGroupById(originalId).getGroupName());

    }
}