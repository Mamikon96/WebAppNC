package dao;

import models.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    User getUserById(long id);
    User getUserByLogin(String login);
    List<User> getAllUsers();
}
