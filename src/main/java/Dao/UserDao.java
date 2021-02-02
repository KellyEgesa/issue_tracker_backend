package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    void saveNewUser(User newUser);
    User getUserById(int id);
}
