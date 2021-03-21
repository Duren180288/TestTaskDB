package app.model;

import app.DAO.Connect;
import app.entities.User;

import java.util.ArrayList;
import java.util.Comparator;

public class Model {
    User user =null;
    ArrayList<User> users = null;

    public User searchUserFromID(int userId) {
        user = new Connect().searchUserFromID(userId);
        return user;
    }

    public User searchUserFromName(String userName) {
        user = new Connect().searchUserFromName(userName);
        return user;
    }


    public ArrayList<User> showAgeSortedUsers() {
        users = new ArrayList<>();
        new Connect().showUsers().stream().sorted((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge())).forEach(users::add);
        return users;
    }

    public User changeUserSurnameFronID(int userId, String userSurname) {
        new Connect().changeUserSurnameFronID(userId, userSurname);
        user = new Connect().searchUserFromID(userId);
        return user;
    }

    public ArrayList<User> getPhoneNumberFromSurname(String surName) {
        users = new ArrayList<>();
        new Connect().showUsers().stream().filter(s->s.getSurname().equals(surName)).forEach(users::add);
        return users;
    }

//import java.util.Comparator;
//        import java.util.stream.Stream;
//
//public class Program {
//
//    public static void main(String[] args) {
//
//        phoneStream.sorted(new PhoneComparator())
//                .forEach(p->System.out.printf("%s (%s) - %d \n",
//                p.getName(), p.getCompany(), p.getPrice()));
//
//                }
//                }
//class PhoneComparator implements Comparator<Phone>{
//
//    public int compare(Phone a, Phone b){
//
//        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
//    }
}