package Dao;

import models.Project;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oProjectTest {

    private static Sql2oProject projectDao;

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeClass
    public static void setUp() {
        projectDao = new Sql2oProject();
    }

    Timestamp rightNow = new Timestamp(new Date().getTime());

    private Project setUpProject() {
        return new Project("Issue Tracker", "Tracking errors of the system", rightNow);
    }

    private Project setUpSecondProject() {
        return new Project("MicroFinance", "Saving small amount of money", rightNow);
    }


    @Test
    public void saveNewProjectSetsCorrectId() {
        Project project = setUpProject();
        int originalId = project.getProjectId();
        projectDao.saveNewProject(project);
        assertNotEquals(originalId, project.getProjectId());
    }

    @Test
    public void getProjectById() {
        Project project = setUpProject();
        Project secondProject = setUpProject();
        projectDao.saveNewProject(project);
        projectDao.saveNewProject(secondProject);
        int originalId = project.getProjectId();
        assertEquals("Issue Tracker", projectDao.getProjectById(originalId).getProjectName());
    }
}