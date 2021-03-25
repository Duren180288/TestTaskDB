package app;

import app.entities.User;
import app.model.Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        User user;
        List<User> users;
        String surname;

        if (!Model.getConnection()) {
            System.out.println("Нет связи с БД!");
            System.exit(0);
        }

        System.out.println("\n**** Работа с СУБД ****");
        System.out.println("Введите номер операции: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.println("\n1 - Показать всех пользователей отсортированных по возрасту");
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
                        users = Model.showAgeSortedUsers();
                        for (User u : users) {
                            System.out.println(u.toString());
                        }
                        break;
                    case "2":
                        System.out.println("Введите ID пользователя: ");
                        try {
                            int id = Integer.parseInt(reader.readLine());
                            user = Model.searchUserFromID(id);
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверный ID");
                        }
                        break;
                    case "3":
                        System.out.println("Введите имя пользователя: ");
                        try {
                            user = Model.searchUserFromName(reader.readLine());
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверное имя");
                        }
                        break;
                    case "4":
                        System.out.println("Введите ID изменяемой учётной записи ");
                        try {
                            int id = Integer.parseInt(reader.readLine());
                            user = Model.searchUserFromID(id);
                            System.out.println("Введите новую фамилию для учетной записи №" + user.getId());
                            user = Model.changeUserSurnameFromID(id, reader.readLine());
                            System.out.println("Обновленная учетная запись №" + id + ":");
                            System.out.println(user.toString());
                        } catch (Exception e) {
                            System.out.println("Неверный ID");
                        }
                        break;
                    case "5":
                        System.out.println("\nТелефонный справочник");
                        System.out.println("1 - Поиск по фамилии");
                        System.out.println("2 - Вывод всего телефонного справочника");
                        System.out.println("0 - Выход!");
                        i = reader.readLine();

                        if (i.equals("0")) {
                            break;
                        }
                        switch (i) {

                            case "1":

                                System.out.println("Введите фамилию абонента ");
                                surname = reader.readLine();
                                users = Model.getPhoneNumberFromSurname(surname);
                                for (User u : users) {
                                    System.out.println("- Фамилия: " + u.getSurname() +
                                            " Имя: " + u.getName() + " Номер телефона: " + u.getPhoneNumber());
                                }
                                break;
                            case "2":
                                users = Model.getPhoneNumberTabel();
                                System.out.println("\n---- Телефонный справочник ----");
                                for (User u : users) {
                                    System.out.println("- Фамилия: " + u.getSurname() +
                                            " Имя: " + u.getName() + " Номер телефона: " + u.getPhoneNumber());
                                }
                                break;
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }
}
