package models;

import java.sql.Timestamp;
import java.util.Date;

public class Project {
    private String projectName;
    private String projectDescription;
    private int ProjectId;
    private Timestamp duration;

    public Project(String projectName, String projectDescription, Timestamp duration) {
        this.duration = duration;
        this.projectDescription = projectDescription;
        this.projectName = projectName;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Timestamp getDuration() {
        return duration;
    }
}
