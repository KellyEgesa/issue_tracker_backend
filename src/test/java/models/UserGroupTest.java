package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserGroupTest {

    private UserGroup setupUserGroup() {
        return new UserGroup(12, 14);
    }

    @Test
    public void userInstantiatesCorrectly() {
        UserGroup testUserGroup = setupUserGroup();
        assertTrue(testUserGroup instanceof UserGroup);
    }

    @Test
    public void getUserId() {
        UserGroup testUserGroup = setupUserGroup();
        assertEquals(12, testUserGroup.getUserId());
    }

    @Test
    public void getGroupId() {
        UserGroup testUserGroup = setupUserGroup();
        assertEquals(14, testUserGroup.getGroupId());
    }
}