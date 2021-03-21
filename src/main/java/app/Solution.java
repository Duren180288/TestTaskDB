package app;

import app.entities.User;
import app.model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        Model model = new Model();
        User user;
        ArrayList<User> users;
        System.out.println("Работа с СУБД");
        System.out.println("Введите номер операции: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.println("1 - Показать всех пользователей отсортированных по возрасту");
                System.out.println("2 - Поиск пользователя по ID ");
                System.out.println("3 - Поиск пользователя по имени ");
                System.out.println("4 - Изменить фамилию в учетной записи ");
                System.out.println("5 - Телефонный справочник ");
                System.out.println("0 - Выход!");

                String i = reader.readLine();
                if (i.equals("0")) {
                    System.out.println("Good Bye!");
                    break;
                }
                switch (i) {
                    case "1":
                        users = model.showAgeSortedUsers();
                        for (User u : users) {
                            System.out.println(u.toString());
                        }
                        break;
                    case "2":
                        System.out.println("Введите ID пользователя: ");
                        try {
                            int id = Integer.parseInt(reader.readLine());
                            user = model.searchUserFromID(id);
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверный ID");
                        }
                        break;
                    case "3":
                        System.out.println("Введите имя пользователя: ");
                        try {
                            user = model.searchUserFromName(reader.readLine());
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверное имя");
                        }
                        break;
                    case "4":
                        System.out.println("Введите ID изменяемой учётной записи ");
                        try {
                            int id = Integer.parseInt(reader.readLine());
                            user = model.searchUserFromID(id);
                            System.out.println("Введине новую фамилию для учетной записи №" + user.getId());
                            user = model.changeUserSurnameFronID(id, reader.readLine());
                            System.out.println("Обновленная учетная запись №" + id + ":");
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверный ID");
                        }
                        break;
                    case "5":
                        System.out.println("Введите фамилию абонента ");
                        try {
                           users =  model.getPhoneNumberFromSurname(reader.readLine());
                            for (User u: users){
                                System.out.println("abonent" + u.getSurname());
                                System.out.println("Номер телефона: "+u.getPhoneNumber());
                           }
                        } catch (Exception e) {
                            System.out.println("Неверная фамилия");
                        }
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }

}
