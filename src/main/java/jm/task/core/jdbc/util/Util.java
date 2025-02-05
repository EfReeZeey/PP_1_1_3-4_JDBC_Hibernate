package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASSWORD = "Qw123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB, USER, PASSWORD);
    }
}
