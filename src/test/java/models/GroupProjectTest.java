package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupProjectTest {

    private GroupProject seUpGroupProject() {
        return new GroupProject(13, 15);
    }

    @Test
    public void groupProjectInstantiatesProperly() {
        GroupProject groupProject = seUpGroupProject();
        assertTrue(groupProject instanceof GroupProject);

    }

    @Test
    public void getGroupId() {
        GroupProject groupProject = seUpGroupProject();
        assertEquals(13, groupProject.getGroupId());
    }

    @Test
    public void getProjectId() {
        GroupProject groupProject = seUpGroupProject();
        assertEquals(15, groupProject.getProjectId());
    }
}