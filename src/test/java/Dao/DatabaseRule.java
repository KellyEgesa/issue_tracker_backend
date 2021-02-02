package Dao;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/issue_tracker_test", "kelly", "kelly@123");
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteUsers = "DELETE FROM users *;";
            String deleteGroups = "DELETE FROM groups *;";
            String deletedProject = "DELETE FROM project *;";
            String deletedTickets = "DELETE FROM tickets *;";
            String deletedGroupProject = "DELETE FROM groupproject *;";
            String deletedTicketUser = "DELETE FROM ticketuser *;";
            String deletedUserGroup = "DELETE FROM usergroup *;";


            con.createQuery(deleteUsers)
                    .executeUpdate();
            con.createQuery(deleteGroups)
                    .executeUpdate();
            con.createQuery(deletedProject)
                    .executeUpdate();
            con.createQuery(deletedTickets)
                    .executeUpdate();
            con.createQuery(deletedGroupProject)
                    .executeUpdate();
            con.createQuery(deletedTicketUser)
                    .executeUpdate();
            con.createQuery(deletedUserGroup)
                    .executeUpdate();
        }
    }
}
