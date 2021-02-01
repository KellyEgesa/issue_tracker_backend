package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User setUpUser() {
        return new User("KellyEgesa", "fBAda12432wre");
    }

    @Test
    public void userInstantiatesCorrectly() {
        User testUser = setUpUser();
        assertTrue(testUser instanceof User);
    }
}