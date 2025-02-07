package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Qw123456";

    public static Connection getConnection() {
        try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD)) {
            System.out.println("Соединение установлено!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение!");
            throw new RuntimeException();
        }
    }
}
