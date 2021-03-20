package app;

import app.DAO.Connect;
import app.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Connect connect = new Connect();
        connect.goConnectToSQL();
        ArrayList<User> users = connect.showUsers();
        for (User u: users){
            System.out.println(u.toString());
        }

   //     System.out.println(connect.searchUserFromID(1));

//        List<String> ad = connect.showAddedUsers();
//        for (String i : ad){
//            System.out.println(i);
//        }
    }
}
