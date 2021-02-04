import Dao.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ApiException;
import models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        Sql2oUser userDao = new Sql2oUser();
        Sql2oGroup groupDao = new Sql2oGroup();
        Sql2oProject projectDao = new Sql2oProject();
        Sql2oTickets ticketsDao = new Sql2oTickets();
        Sql2oUserGroup userGroupDao = new Sql2oUserGroup();
        Sql2oTicketUsers ticketUsersDao = new Sql2oTicketUsers();
        Gson gson = new Gson();

        post("/user/new", "application/json", (req, res) -> {
            User newUser = gson.fromJson(req.body(), User.class);
            userDao.saveNewUser(newUser);
            res.status(201);
            return gson.toJson(newUser);
        });

        get("/user/:id", "application/json", ((request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            User user = userDao.getUserById(userId);
            response.status(201);
            return gson.toJson(user);
        }));

        get("/user/firebase/:fireBaseUserId", "application/json", ((request, response) -> {
            String fireBaseUserId = request.params("fireBaseUserId");
            User user = userDao.getUserByFirebase(fireBaseUserId);
            List<Group> groups = userGroupDao.getUsersGroup(user.getUserId());

            GetUserDetails getUserDetails = new GetUserDetails(user, groups);

            response.status(201);
            return gson.toJson(getUserDetails);
        }));

        get("/user/:email", "application/json", ((request, response) -> {
            String email = request.params("email");
            User user = userDao.getUserByEmail(email);
            response.status(201);
            if (user == null) {
                response.status(400);
            }
            return gson.toJson(user);
        }));

        post("/group/new", "application/json", (req, res) -> {
            Group newGroup = gson.fromJson(req.body(), Group.class);
            groupDao.createNewGroup(newGroup);
            userGroupDao.addUserToGroup(newGroup.getUserAdminId(), newGroup.getGroupId());
            res.status(201);
            return gson.toJson(newGroup);
        });

        get("/group/:id/", "application/json", ((request, response) -> {
            int groupId = Integer.parseInt(request.params("id"));
            Group group = groupDao.getGroupById(groupId);
            List<Project> projects = projectDao.getProjectByGroupId(group.getGroupId());
            List<User> users = userGroupDao.getGroupUsers(group.getGroupId());
            List<Tickets> tickets = null;
            if (projects.size() > 0) {
                tickets = ticketsDao.getTicketsByProjectId(projects.get(0).getProjectId());
            }

            GetAll getAll = new GetAll(group, projects, users, tickets);

            response.status(201);
            return gson.toJson(getAll);
        }));

        post("/project/new", "application/json", (req, res) -> {
            Project newProject = gson.fromJson(req.body(), Project.class);
            projectDao.saveNewProject(newProject);
            res.status(201);
            return gson.toJson(newProject);
        });

        get("/project/:id", "application/json", ((request, response) -> {
            int projectId = Integer.parseInt(request.params("id"));
            Project project = projectDao.getProjectById(projectId);
            response.status(201);
            return gson.toJson(project);
        }));

        get("/projectGroup/:id", "application/json", ((request, response) -> {
            int groupId = Integer.parseInt(request.params("id"));
            List<Project> project = projectDao.getProjectByGroupId(groupId);
            response.status(201);
            return gson.toJson(project);
        }));

        post("/ticket/new", "application/json", (req, res) -> {
            Tickets tickets = gson.fromJson(req.body(), Tickets.class);
            ticketsDao.saveNewTicket(tickets);
            res.status(201);
            return gson.toJson(tickets);
        });

        get("/ticket/:id", "application/json", ((request, response) -> {
            int ticketId = Integer.parseInt(request.params("id"));
            Tickets tickets = ticketsDao.getTicketById(ticketId);
            response.status(201);
            return gson.toJson(tickets);
        }));

        get("/ticketProject/:id", "application/json", ((request, response) -> {
            int projectId = Integer.parseInt(request.params("id"));
            List<Tickets> tickets = ticketsDao.getTicketsByProjectId(projectId);
            response.status(201);
            return gson.toJson(tickets);
        }));

        put("/ticketStatus/:id/:status", "application/json", ((request, response) -> {
            int ticketId = Integer.parseInt(request.params("id"));
            int status = Integer.parseInt(request.params("status"));
            ticketsDao.updateTicketStatus(ticketId, status);
            response.status(201);
            return gson.toJson("");
        }));

        put("/ticketPriority/:id/:priority", "application/json", ((request, response) -> {
            int ticketId = Integer.parseInt(request.params("id"));
            int priority = Integer.parseInt(request.params("priority"));
            ticketsDao.updateTicketStatus(ticketId, priority);
            response.status(201);
            return gson.toJson("");
        }));

        post("/addUser", "application/json", (req, res) -> {
            UserGroup newGroup = gson.fromJson(req.body(), UserGroup.class);
            userGroupDao.addUserToGroup(newGroup.getUserId(), newGroup.getGroupId());
            List<User> users = userGroupDao.getGroupUsers(newGroup.getGroupId());
            res.status(201);
            return gson.toJson(users);
        });

        get("/userGroup/:id", "application/json", ((request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            List<Group> groups = userGroupDao.getUsersGroup(userId);
            response.status(201);
            return gson.toJson(groups);
        }));

        get("/groupUser/:id", "application/json", ((request, response) -> {
            int groupId = Integer.parseInt(request.params("id"));
            List<User> users = userGroupDao.getGroupUsers(groupId);
            response.status(201);
            return gson.toJson(users);
        }));

        delete("/removeUser", "application/json", ((request, response) -> {
            UserGroup newGroup = gson.fromJson(request.body(), UserGroup.class);
            userGroupDao.deleteUserFromGroup(newGroup.getUserId(), newGroup.getGroupId());
            List<User> users = userGroupDao.getGroupUsers(newGroup.getGroupId());
            response.status(201);
            return gson.toJson(users);
        }));

        post("/ticketUser", "application/json", (req, res) -> {
            TicketUser newUser = gson.fromJson(req.body(), TicketUser.class);
            ticketUsersDao.addTicketToUser(newUser.getUserId(), newUser.getTicketId());
            List<User> users = ticketUsersDao.getTicketUsers(newUser.getTicketId());
            res.status(201);
            return gson.toJson(users);
        });

        get("/usersTicket/:id", "application/json", ((request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            List<Tickets> tickets = ticketUsersDao.getUsersTickets(userId);
            response.status(201);
            return gson.toJson(tickets);
        }));

        get("/ticketUsers/:id", "application/json", ((request, response) -> {
            int ticketsId = Integer.parseInt(request.params("id"));
            List<User> users = ticketUsersDao.getTicketUsers(ticketsId);
            response.status(201);
            return gson.toJson(users);
        }));


        after(((request, response) -> {
            response.type("application/json");
        }));

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
    }
}
