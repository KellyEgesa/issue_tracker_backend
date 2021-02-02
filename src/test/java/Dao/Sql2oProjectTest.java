package Dao;

import models.Group;
import models.Project;
import models.User;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oProjectTest {

    private static Sql2oProject projectDao;
    private static Sql2oUser userDao;
    private static Sql2oGroup groupDao;

    private Group setUpGroup() {
        User user = new User("KellyEgesa", "fBAda12432wre", "kelly.egesa@gmail.com");
        userDao.saveNewUser(user);
        return new Group("Microsoft", user.getUserId());
    }

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();


    @BeforeClass
    public static void setUp() {
        projectDao = new Sql2oProject();
        userDao = new Sql2oUser();
        groupDao = new Sql2oGroup();
    }

    Timestamp rightNow = new Timestamp(new Date().getTime());
    Group group = setUpGroup();

    private Project setUpProject() {
        groupDao.createNewGroup(group);
        return new Project("Issue Tracker", "Tracking errors of the system", group.getGroupId(), rightNow);
    }

    private Project setUpSecondProject() {
        return new Project("MicroFinance", "Saving small amount of money", group.getGroupId(), rightNow);
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

    @Test
    public void getProjectByGroupIdReturnsTheCorrectProjects() {
        Project project = setUpProject();
        Project secondProject = setUpSecondProject();
        projectDao.saveNewProject(project);
        projectDao.saveNewProject(secondProject);
        int groupId = group.getGroupId();
        assertEquals(2, projectDao.getProjectByGroupId(groupId).size());
    }
}