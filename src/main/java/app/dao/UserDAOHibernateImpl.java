package app.dao;

import app.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean validateUser(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null) {
            return getUserByLogin(login).getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        return sessionFactory.openSession().get(User.class, login);
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
                printTransactionError();
            } catch (Exception eRoll) {
                printRollbackError();
            }
        }
    }

    private void printRollbackError() {
        System.err.println("Couldn't rollback transaction");
    }

    private void printTransactionError() {
        System.err.println("Error happened, transaction rollback");
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
                printTransactionError();
            } catch (Exception eRoll) {
                printRollbackError();
            }
        }
    }

    @Override
    public void deleteUser(String login) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = getUserByLogin(login);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
                printTransactionError();
            } catch (Exception eRoll) {
                printRollbackError();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.openSession().createQuery("From User").getResultList();
//        return (List<User>) sessionFactory.openSession().createQuery("From User").list();
    }
}
