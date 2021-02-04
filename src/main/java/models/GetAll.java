package models;

import java.util.List;

public class GetAll {
    private Group group;
    private List<Project> projectList;
    private List<User> users;
    private List<Tickets> tickets;

    public GetAll(Group group, List<Project> projectList, List<User> users, List<Tickets> tickets) {
        this.group = group;
        this.projectList = projectList;
        this.users = users;
        this.tickets = tickets;
    }

    public Group getGroupGetAll() {
        return group;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Tickets> getTicketsGetAll() {
        return tickets;
    }
}
