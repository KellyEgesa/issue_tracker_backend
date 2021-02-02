package Dao;

import models.Project;

import java.util.List;

public interface ProjectDao {
    void saveNewProject(Project newProject);
    Project getProjectById(int id);
    List<Project> getProjectByGroupId(int groupId);
}
