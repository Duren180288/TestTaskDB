package app.model;

import app.DAO.Connect;
import app.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Model {
    private static User user = null;
    private static List<User> users = null;

    public static boolean getConnection() {
        return new Connect().goConnectToSQL();
    }

    public static User searchUserFromID(int userId) {
        user = new Connect().searchUserFromID(userId);
        if (user == null) {
            System.out.println("Ошибка БД!");
        }
        return user;
    }

    public static User searchUserFromName(String userName) {
        user = new Connect().searchUserFromName(userName);
        if (user == null) {
            System.out.println("Ошибка БД!");
        }
        return user;
    }

    public static List<User> showAgeSortedUsers() {
        users = new ArrayList<>();
        List<User> usersFromDB = new Connect().showUsers();
        if (usersFromDB.isEmpty()) {
            System.out.println("Ошибка! БД пуста.");
        } else
            usersFromDB.stream().sorted(Comparator.comparingInt(User::getAge)).forEach(users::add);
        return users;
    }

    public static User changeUserSurnameFromID(int userId, String userSurname) {
        user = searchUserFromID(userId);
        new Connect().changeUserSurnameFromID(userId, userSurname);
        user = new Connect().searchUserFromID(userId);
        return user;
    }

    public static List<User> getPhoneNumberFromSurname(String surName) {
        users = new ArrayList<>();
        List<User> usersFromDB = new Connect().showUsers();
        if (usersFromDB.isEmpty()) {
            System.out.println("Ошибка! БД пуста.");
        } else
            usersFromDB.stream().filter(s -> s.getSurname().equals(surName)).forEach(users::add);
        if (!users.isEmpty()) {
            return users;
        } else
            System.out.println("Нет абонента с такой фамилией!");
        return users;
    }

    public static List<User> getPhoneNumberTabel() {

        users = new Connect().showUsers();
        if (users.isEmpty()) {
            System.out.println("Ошибка! Телефонный справочник пуст.");
        }
        return users;
    }
}


