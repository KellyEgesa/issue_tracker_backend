package models;

public class GroupProject {
    private int groupId;
    private int projectId;

    public GroupProject(int groupId, int projectId){
        this.groupId = groupId;
        this.projectId = projectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getProjectId() {
        return projectId;
    }
}
