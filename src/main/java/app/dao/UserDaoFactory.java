package app.dao;

import app.utils.DBHelper;

public class UserDaoFactory {

    public static UserDAO getUserDAO(String daoType) {
        UserDAO userDAO = null;
        if(daoType.equals("hibernate")) {
            userDAO = new UserDAOHibernateImpl(DBHelper.getDbHelper().getConfiguration().buildSessionFactory());
        } else if (daoType.equals("jdbc")) {
            userDAO = new UserDAOjdbcImpl(DBHelper.getDbHelper().getConnection());
        }
        return userDAO;
    }
}
