package app.dao;

import app.dao.executor.Executor;
import app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOjdbcImpl implements UserDAO {
    private Connection connection;
    private Executor executor;

    public UserDAOjdbcImpl(Connection connection) {
        this.connection = connection;
        executor = new Executor(connection);
    }

    @Override
    public boolean validateUser(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return executor.execQuery("select * from users where login= '" + login + "'", result -> {
                if (result.next()) {
                    return new User(result.getString("login"),
                            result.getString("password"),
                            result.getString("name"),
                            result.getDouble("amount"),
                            result.getString("role"));
                } else {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        try {
            executor.execUpdate("insert into users (login, password, name, amount) values ('" + user.getLogin() + "', '" +
                    user.getPassword() + "', '" + user.getName() + "', '" + user.getAmount() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String newPassword = user.getPassword();
        String newName = user.getName();
        double newAmount = user.getAmount();
        String newRole = user.getRole();
        String sql = "update users set password=?, name=?, amount=?, role=? where login=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, newName);
            stmt.setDouble(3, newAmount);
            stmt.setString(4, newRole);
            stmt.setString(5, user.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(String login) {
        try {
            executor.execUpdate("delete from users where login='" + login + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            return executor.execQuery("select * from users", result -> {
                while (result.next()) {
                    usersList.add(new User(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getDouble(4),
                            result.getString(5)));
                }
                return usersList;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
