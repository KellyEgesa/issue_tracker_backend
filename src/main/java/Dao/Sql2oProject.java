package Dao;

import models.Project;
import org.sql2o.Connection;

public class Sql2oProject implements ProjectDao{
    @Override
    public void saveNewProject(Project newProject) {
        String sql = "INSERT INTO project (projectName ,projectDescription , duration) VALUES (:projectName ,:projectDescription , :duration)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("projectName", newProject.getProjectName())
                    .addParameter("projectDescription", newProject.getProjectDescription())
                    .addParameter("duration", newProject.getDuration())
                    .executeUpdate()
                    .getKey();
            newProject.setProjectId(id);
        }
    }

    @Override
    public Project getProjectById(int id) {
        String sql = "SELECT * FROM project WHERE projectId = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Project.class);
        }
    }
}
