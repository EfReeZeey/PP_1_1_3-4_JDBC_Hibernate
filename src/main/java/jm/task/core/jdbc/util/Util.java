package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Qw123456";
    public static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD)) {
            System.out.println("Соединение установлено!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение!");
            e.printStackTrace();
            return null;
        }
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                Properties prop = new Properties();
                prop.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
                prop.setProperty(Environment.URL, DB);
                prop.setProperty(Environment.USER, USER);
                prop.setProperty(Environment.PASS, PASSWORD);
                prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                prop.setProperty(Environment.HBM2DDL_AUTO, "");

                config.setProperties(prop);
                config.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();

                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка в SessionFactory");
            }
        }
        return sessionFactory;
    }
}
