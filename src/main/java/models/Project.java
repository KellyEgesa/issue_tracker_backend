package models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Project {
    private String projectName;
    private String projectDescription;
    private int projectId;
    private Timestamp duration;

    public Project(String projectName, String projectDescription, Timestamp duration) {
        this.duration = duration;
        this.projectDescription = projectDescription;
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return getProjectId() == project.getProjectId() &&
                Objects.equals(getProjectName(), project.getProjectName()) &&
                Objects.equals(getProjectDescription(), project.getProjectDescription()) &&
                Objects.equals(getDuration(), project.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectName(), getProjectDescription(), getProjectId(), getDuration());
    }
}
