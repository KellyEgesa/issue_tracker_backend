package Dao;

import models.User;
import org.sql2o.Connection;

public class Sql2oUser implements UserDao {
    @Override
    public void saveNewUser(User newUser) {
        String sql = "INSERT INTO users (fireBaseUserId ,userName , email) VALUES (:fireBaseUserId ,:userName , :email)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("fireBaseUserId", newUser.getFireBaseUserId())
                    .addParameter("userName", newUser.getUsername())
                    .addParameter("email", newUser.getEmail())
                    .executeUpdate()
                    .getKey();
            newUser.setUserId(id);
        }

    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE userId = :id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User getUserByFirebase(String fireBaseUserId) {
        String sql = "SELECT * FROM users WHERE fireBaseUserId = :fireBaseUserId";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("fireBaseUserId", fireBaseUserId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("email", email)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(User.class);
        }
    }
}
