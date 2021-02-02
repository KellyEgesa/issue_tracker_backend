package Dao;

import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oUserTest {
    private static Sql2oUser userDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        userDao = new Sql2oUser();
    }

    private User setUpUser() {
        return new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
    }

    private User setUpSecondUser() {
        return new User("Brian Omondi", "fBsdr122132wre", "brian.omondi@gmail.com");
    }

    @Test
    public void saveNewUserSetsId() {
        User user = setUpUser();
        int originalId = user.getUserId();
        userDao.saveNewUser(user);
        assertNotEquals(originalId, user.getUserId());
    }

    @Test
    public void getUserByIdReturnsCorrectUser() {
        User user = setUpUser();
        User secondUser = setUpSecondUser();
        userDao.saveNewUser(user);
        userDao.saveNewUser(secondUser);
        int originalId = user.getUserId();
        assertEquals("KellyEgesa", userDao.getUserById(originalId).getUsername());
    }
}