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

    @Test
    public void createNewGroupSetsIdCorrectly() {
    }

    @Test
    public void getGroupById() {
    }
}