package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sqlQueryForCreateTable = "CREATE TABLE IF NOT EXISTS user(\n" +
                                        "id INT8 AUTO_INCREMENT PRIMARY KEY,\n" +
                                        "name VARCHAR(64),\n" +
                                        "last_name VARCHAR(64),\n" +
                                        "age INT2)";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery(sqlQueryForCreateTable).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sqlQueryForDropTable = "drop table IF EXISTS user";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery(sqlQueryForDropTable).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();

            session.save(user);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User user;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE user WHERE id = :id").setParameter("id", id).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> listUsersFromUserTable = new ArrayList<>();
        String queryGetAllUsersFromUserTable = "FROM user";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query usersFromUserTable = session.createQuery(queryGetAllUsersFromUserTable);
            listUsersFromUserTable = usersFromUserTable.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return listUsersFromUserTable;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        String queryDeleteAllDataInUserTable = "DELETE user";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery(queryDeleteAllDataInUserTable).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

