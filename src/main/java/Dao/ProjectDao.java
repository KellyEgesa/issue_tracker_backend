package Dao;

import models.Project;

public interface ProjectDao {
    void saveNewProject(Project newProject);
    Project getProjectById(int id);
}
