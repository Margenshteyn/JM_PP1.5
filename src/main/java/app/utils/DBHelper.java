package app.utils;


import app.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;

public class DBHelper {
    private static DBHelper dbHelper;

    /*    private static SessionFactory sessionFactory;*/
    public static DBHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    /*    public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                sessionFactory = createSessionFactory();
            }
            return sessionFactory;
        }*/
    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/new_schema1.1?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "7CthdthVfhutyintqyf6");
        configuration.setProperty("hibernate.show_sql", "true");

        configuration.setProperty("connection.pool_size", "10");
        configuration.setProperty("current_session_context_class", "thread");

//        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        return configuration;
    }

    public static Connection getConnection() {
        return ConnectionProvider.getMysqlConnection();
    }
 /*   private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }*/

}

