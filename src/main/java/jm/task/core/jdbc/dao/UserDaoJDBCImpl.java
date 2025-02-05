package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";

        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица users создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица users удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = Util.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных!\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = Util.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.printf("User с id %d удалён!\n", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Statement stmt = Util.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица users очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
