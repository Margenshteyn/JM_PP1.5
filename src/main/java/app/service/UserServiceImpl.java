package app.service;


import app.dao.UserDAO;
import app.dao.UserDaoFactory;

import app.entities.User;
import app.utils.PropertyReaderHelper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserDAO userDAO;
    private static UserServiceImpl userService;

    private UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            String daoType = new PropertyReaderHelper().getProperty("typeDAO");
            UserDAO userDAO = UserDaoFactory.getUserDAO(daoType);
            userService = new UserServiceImpl(userDAO);
        }
        return userService;
    }

    @Override
    public boolean addUser (User user){
        if (getUserByLogin(user.getLogin()) != null) {
            return false;
        }
        userDAO.addUser(user);
        return true;
    }

    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public boolean updateUser (User user, String password){
        if (validateUser(user.getLogin(), password)) {
            userDAO.updateUser(user);
            return true;
        }
        return false;
    }

    public boolean validateUser(String login, String password) {
        return userDAO.validateUser(login, password);
    }

    @Override
    public void deleteUser (String login){
        userDAO.deleteUser(login);
    }

    @Override
    public List<User> getUsersList () {
        return userDAO.getAllUsers();
    }
}
