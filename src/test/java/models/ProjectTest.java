package models;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class ProjectTest {
    Timestamp rightNow = new Timestamp(new Date().getTime());

    private Project setUpProject() {
        return new Project("Issue Tracker", "Tracking errors of the system", 1, rightNow);
    }

    @Test
    public void projectInstantiatesProperly() {
        Project testProject = setUpProject();
        assertTrue(testProject instanceof Project);
    }

    @Test
    public void setProjectIdSetsIdCorrectly_1() {
        Project testProject = setUpProject();
        testProject.setProjectId(1);
        assertEquals(1, testProject.getProjectId());
    }

    @Test
    public void getProjectNameReturnsCorrectName() {
        Project testProject = setUpProject();
        assertEquals("Issue Tracker", testProject.getProjectName());
    }

    @Test
    public void getProjectDescriptionReturnsCorrectDescription() {
        Project testProject = setUpProject();
        assertEquals("Tracking errors of the system", testProject.getProjectDescription());
        assertEquals(1, testProject.getGroupId());
    }

    @Test
    public void getDurationReturnsCorrectDuration() {
        Project testProject = setUpProject();
        assertEquals(rightNow.getDate(), testProject.getDuration().getDate());
    }

    @Test
    public void equalsOperatesWell() {
        Project testProject = setUpProject();
        Project anotherTestProject = new Project("Issue Tracker", "Tracking errors of the system", 1, rightNow);
        assertTrue(testProject.equals(anotherTestProject));
    }
}