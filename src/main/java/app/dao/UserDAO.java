package app.dao;

import app.entities.User;

import java.util.List;

public interface UserDAO {
    public boolean validateUser(String login, String password);
    public User getUserByLogin(String login);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(String login);
    public List<User> getAllUsers();
}
