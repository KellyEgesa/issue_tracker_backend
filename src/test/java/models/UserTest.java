package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User setUpUser() {
        return new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
    }

    @Test
    public void userInstantiatesCorrectly() {
        User testUser = setUpUser();
        assertTrue(testUser instanceof User);
    }

    @Test
    public void setUserIdSetsIdCorrectly_1() {
        User testUser = setUpUser();
        testUser.setUserId(1);
        assertEquals(1, testUser.getUserId());
    }

    @Test
    public void getUsernameGetsUsernameCorrectly_KellyEgesa() {
        User testUser = setUpUser();
        assertEquals("KellyEgesa", testUser.getUsername());
    }

    @Test
    public void getFireBaseUserIdGetsFireBaseUserIdCorrectly_fBAda12432wre() {
        User testUser = setUpUser();
        assertEquals("fBAda12432wre", testUser.getFireBaseUserId());

    }

    @Test
    public void getEmailGetsEmailCorrectly() {
        User testUser = setUpUser();
        assertEquals("kelly.egesa@gmail.com", testUser.getEmail());
    }
}