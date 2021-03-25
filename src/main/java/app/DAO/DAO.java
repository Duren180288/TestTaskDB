package app.DAO;

import app.entities.User;

import java.util.List;

public interface DAO {
    boolean goConnectToSQL();
    List<User> showUsers();
    User searchUserFromID(int userId);
    User searchUserFromName(String userName);
    void changeUserSurnameFromID(int userId, String userSurname);
}
