package app.model;

import app.DAO.Connect;
import app.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Model {
    User user = null;
    ArrayList<User> users = null;

    public boolean getConnection() {
        return new Connect().goConnectToSQL();
    }

    public User searchUserFromID(int userId) {
        user = new Connect().searchUserFromID(userId);
        if (user == null) {
            System.out.println("Ошибка БД!");
        }
        return user;
    }

    public User searchUserFromName(String userName) {
        user = new Connect().searchUserFromName(userName);
        if (user == null) {
            System.out.println("Ошибка БД!");
        }
        return user;
    }

    public ArrayList<User> showAgeSortedUsers() {
        users = new ArrayList<>();
        List<User> usersFromDB = new Connect().showUsers();
        if (usersFromDB.isEmpty()) {
            System.out.println("Ошибка! БД пуста.");
        } else
            new Connect().showUsers().stream().sorted(Comparator.comparingInt(User::getAge)).forEach(users::add);
        return users;
    }

    public User changeUserSurnameFromID(int userId, String userSurname) {
        user = searchUserFromID(userId);
        new Connect().changeUserSurnameFromID(userId, userSurname);
        user = new Connect().searchUserFromID(userId);
        return user;
    }

    public List<User> getPhoneNumberFromSurname(String surName) {
        users = new ArrayList<>();
        new Connect().showUsers().stream().filter(s -> s.getSurname().equals(surName)).forEach(users::add);
        if (!users.isEmpty()) {
            return users;
        } else
            return new Connect().showUsers();
    }

    public List<User> getPhoneNumberTabel() {

        users = (ArrayList<User>) new Connect().showUsers();
        if (users.isEmpty()) {
            System.out.println("Ошибка! Телефонный справочник пуст.");
        }
        return users;
    }
}


