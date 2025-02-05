package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Pete", "Jenkins", (byte) 20);
        userDao.saveUser("Jim", "Raynor", (byte) 25);
        userDao.saveUser("Richard", "Tea", (byte) 31);
        userDao.saveUser("Maxim", "Maximoff", (byte) 120);

        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
