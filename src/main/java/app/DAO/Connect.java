package app.DAO;

import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    private static final String USER = "root";
    private static final String PASSWORD = "180288";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dur?serverTimezone=Europe/Moscow&useSSL=false";



    public void goConnectToSQL() {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("Registering JDBC driver...");
            //        Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            System.out.println("Creating table in selected database...");

            try {
                ResultSet Rs = statement.executeQuery("SELECT * FROM dur.UsersTabel");
                if (Rs != null) {
                    System.out.println("Table Users already created");
                }
            } catch (Exception e) {
                getCreate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Учетная запись должна содержать только имя, фамилию,возраст и номер телефона?
    public void getCreate() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE UsersTabel (UserId int auto_increment primary key, " +
                    "UserName varchar(30) not null," +
                    "UserSurname varchar(30) not null, UserAge int (3) not null," +
                    "UserPhone varchar(30) not null)");
            System.out.println("Table Users successfully created...");
        } catch (SQLException s) {
            s.getSQLState();
        }
    }

//    public void add(User user) {
//        String sqlInsert = "INSERT INTO users (UserName, Password) value (?, ?)";
//        if (!user.getName().isEmpty()) {
//            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
//                 PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
//                System.out.println("Insert to DB");
//                String UserName = user.getName();
//                String password = user.getPassword();
//                System.out.println(UserName + " " + password);
//                preparedStatement.setString(1, UserName);
//                preparedStatement.setString(2, password);
//                int rows = preparedStatement.executeUpdate(); //not insert without this string
//                System.out.println(rows + "row  was added!");
//            } catch (SQLException s) {
//                s.getSQLState();
//            }
//        }
//    }



    public ArrayList<User> showUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM userstabel");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age =resultSet.getInt(4);
                String phoneNumber =resultSet.getString(5);
                user = new User(id, name, surname, age, phoneNumber);
                users.add(user);
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
        return users;

    }
//
//    public void deleteUser(String userName) {
//        String sqlDelete = "DELETE FROM users WHERE UserName = ?";
//        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
//            preparedStatement.setString(1, userName);
//            System.out.println("Delete " + userName + " from DB");
//            int rows = preparedStatement.executeUpdate();
//            System.out.println(rows + " row was deleted");
//        } catch (SQLException s) {
//            s.getSQLState();
//        }
//    }
//
    public User searchUserFromID(int userId) {
        String sqlSelect = ("SELECT * FROM dur.UsersTabel WHERE UserId = ?");
        User res = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, userId);
            System.out.println("Select " + userId + " from DB");

            ResultSet resultSet= preparedStatement.executeQuery();
//            while (resultSet.next()){
//                // System.out.println(resultSet.getString("Password"));
             int id = resultSet.getInt("UserId");
            System.out.println(id);
             String name = resultSet.getNString("UserName");
             String surname = resultSet.getNString("UserSurname");
             int age = resultSet.getInt("UserAge");
             String phoneNumber = resultSet.getNString("UserPhone");
//
//                res = "Пользователь #"+resultSet.getNString("UserId") +" "+
//                        resultSet.getNString("UserSurname")+" "+
//                        resultSet.getNString("UserName")+" возраст: "+
//                        resultSet.getNString("UserAge")+" номер телефона:"+
//                        resultSet.getNString("UserPhone");
            res = new User(id, name, surname, age, phoneNumber);

//            }

        } catch (SQLException s) {
            s.getSQLState();
        }
        return res;
    }
}

