package app.DAO;

import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect implements DAO {
    private static final String USER = "root";
    private static final String PASSWORD = "180288";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dur?serverTimezone=Europe/Moscow&useSSL=false";

    public boolean goConnectToSQL() {
        boolean connect = true;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("Registering JDBC driver...");
            System.out.println("Creating connection to database...");
        } catch (SQLException e) {
            connect = false;
        }
        return connect;
    }

    public List<User> showUsers() {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM userstabel");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phoneNumber = resultSet.getString(5);
                user = new User(id, name, surname, age, phoneNumber);
                users.add(user);
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
        return users;
    }

    public User searchUserFromID(int userId) {
        String sqlSelect = "SELECT  * FROM userstabel WHERE UserId = ?";
        User user = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, userId);
            // System.out.println("Select " + userId + " from DB");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phoneNumber = resultSet.getString(5);
                user = new User(id, name, surname, age, phoneNumber);
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
        return user;
    }

    public User searchUserFromName(String userName) {
        String sqlSelect = "SELECT  * FROM userstabel WHERE UserName = ?";
        User user = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setString(1, userName);
            // System.out.println("Select " + userName + " from DB");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phoneNumber = resultSet.getString(5);
                user = new User(id, name, surname, age, phoneNumber);
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
        return user;
    }

    public void changeUserSurnameFromID(int userId, String userSurname) {
        String sqlSelect = "UPDATE userstabel SET userSurname = ? WHERE userId = ?";
        User user = null;
        try {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
                preparedStatement.setString(1, userSurname);
                preparedStatement.setInt(2, userId);
                //  System.out.println("Update ID#" + userId + " from DB");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
    }
}
