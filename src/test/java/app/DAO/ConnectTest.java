package app.DAO;

import app.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectTest {
    private static Connect mockedConnect;
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;

    @Before
    public void setUp() throws Exception {
        mockedConnect = mock(Connect.class);

        user1 = new User(1, "Aleksey", "Iwanow", 44, "1234567");
        user2 = new User(2, "Marina", "Sidorowa", 22, "7654321");
        user3 = new User(3, "Jana", "Petrowa", 11, "9873215");
        user4 = new User(4, "Andrey", "Goncharow", 33, "8568488");

        when(mockedConnect.showUsers()).thenReturn( Arrays.asList(user1, user2, user3, user4));
        when(mockedConnect.searchUserFromID(4)).thenReturn(user4);
        when(mockedConnect.searchUserFromName("Jana")).thenReturn(user3);
        doNothing().when(mockedConnect).changeUserSurnameFromID(1, "Ulanow");
    }

//
//    @Test
//    public void goConnectToSQL() {
//        Assert.assertEquals(4,2+3);
//    }

    @Test
    public void showUsers() {
        List<User> users = mockedConnect.showUsers();
        assertEquals(4, users.size());
        User user = users.get(1);
        assertEquals(2, user.getId());
        assertEquals("Marina", user.getName());
        assertEquals("Sidorowa", user.getSurname());
        assertEquals(22, user.getAge());
        assertEquals("7654321", user.getPhoneNumber());
    }

    @Test
    public void searchUserFromID() {
        int id = 4;
        User user = mockedConnect.searchUserFromID(id);
        assertNotNull(user);

        assertEquals(id, user.getId());
        assertEquals("Andrey", user.getName());
        assertEquals("Goncharow", user.getSurname());
        assertEquals(33, user.getAge());
        assertEquals("8568488", user.getPhoneNumber());

    }

    @Test
    public void searchUserFromName() {
        String name = "Jana";
        User user = mockedConnect.searchUserFromName(name);
        assertNotNull(user);

        assertEquals(name, user.getName());
        assertEquals("Jana", user.getName());
        assertEquals("Petrowa", user.getSurname());
        assertEquals(11, user.getAge());
        assertEquals("9873215", user.getPhoneNumber());


    }

    @Test
    public void changeUserSurnameFromID() {
        user1.setSurname("Ulanow");
        assertEquals("Ulanow", user1.getSurname());
    }
}