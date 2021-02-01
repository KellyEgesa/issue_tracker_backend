package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    private Group setUpGroup() {
        return new Group("Microsoft", 1);
    }

    @Test
    public void groupInstantiatesCorrectly() {
        Group testGroup = setUpGroup();
        assertTrue(testGroup instanceof Group);
    }

    @Test
    public void setGroupIdSetsIdCorrectly_1() {
        Group testGroup = setUpGroup();
        testGroup.setGroupId(1);
        assertEquals(1, testGroup.getGroupId());
    }

    @Test
    public void getGroupNameReturnsCorrectValue() {
        Group testGroup = setUpGroup();
        assertEquals("Microsoft", testGroup.getGroupName());
    }

    @Test
    public void getUserAdminIdReturnsCorrectValue() {
        Group testGroup = setUpGroup();
        assertEquals(1, testGroup.getUserAdminId());
    }

    @Test
    public void equalsWorksAsExpected() {
        Group testGroup = setUpGroup();
        Group anotherTestGroup = new Group("Microsoft", 1);
        assertTrue(testGroup.equals(anotherTestGroup));
    }
}