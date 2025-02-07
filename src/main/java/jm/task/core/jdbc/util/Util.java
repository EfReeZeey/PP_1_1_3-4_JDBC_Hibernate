package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Qw123456";

    public static Connection getConnection() {
        try {
            System.out.println("Соединение установлено!");
            return DriverManager.getConnection(DB, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение!");
            throw new RuntimeException();
        }
    }
}
