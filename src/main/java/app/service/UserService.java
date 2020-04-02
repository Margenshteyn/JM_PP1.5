package app.service;

import app.entities.User;

import java.util.List;

public interface UserService {

    public boolean addUser(User user);

    public boolean updateUser(User user, String password);

    public void deleteUser(String login);

    public List<User> getUsersList();



    public User getUserByLogin(String login);
    public boolean validateUser(String login, String password);

}
