package app.model;

import app.DAO.Connect;
import app.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ModelTest {
    private static Connect mockedConnect;
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;

    @Before
    public void setUp() {
        mockedConnect = mock(Connect.class);
        user1 = new User(1, "Aleksey", "Iwanow", 44, "1234567");
        user2 = new User(2, "Marina", "Sidorowa", 22, "7654321");
        user3 = new User(3, "Jana", "Petrowa", 11, "9873215");
        user4 = new User(4, "Andrey", "Goncharow", 33, "8568488");

        when(mockedConnect.showUsers()).thenReturn(Arrays.asList(user1, user2, user3, user4));
        when(mockedConnect.searchUserFromID(1)).thenReturn(user1);
        when(mockedConnect.searchUserFromID(6)).thenThrow(new IllegalArgumentException());
        when(mockedConnect.searchUserFromName("Marina")).thenReturn(user2);
        when(mockedConnect.searchUserFromName("Roma")).thenThrow(new IllegalArgumentException());
        doNothing().when(mockedConnect).changeUserSurnameFromID(1, "Ulanow");
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchUserFromID() {
        int id = 1;
        User user = mockedConnect.searchUserFromID(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("Aleksey", user.getName());
        assertEquals("Iwanow", user.getSurname());
        assertEquals(44, user.getAge());
        assertEquals("1234567", user.getPhoneNumber());

        mockedConnect.searchUserFromID(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchUserFromName() {
        String name = "Marina";
        User user = mockedConnect.searchUserFromName(name);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals("Marina", user.getName());
        assertEquals("Sidorowa", user.getSurname());
        assertEquals(22, user.getAge());
        assertEquals("7654321", user.getPhoneNumber());

        mockedConnect.searchUserFromName("Roma");

    }

    @Test
    public void showAgeSortedUsers() {
        List<User> usersFromDB = mockedConnect.showUsers();
        List<User> expectedUser = new ArrayList<>();
        List<User> actualUsers = new ArrayList<>();
        actualUsers.add(user3);
        actualUsers.add(user2);
        actualUsers.add(user4);
        actualUsers.add(user1);
        usersFromDB.stream().sorted(Comparator.comparingInt(User::getAge)).forEach(expectedUser::add);
        Assert.assertEquals(expectedUser, actualUsers);
    }

    @Test
    public void changeUserSurnameFromID() {
        user1.setSurname("Ulanow");
        List<User> users = mockedConnect.showUsers();
        assertNotNull(users);
        User user = users.get(0);
        assertEquals("Ulanow", user.getSurname());


    }

    @Test
    public void getPhoneNumberFromSurname() {
        String surName = "Goncharow";
        List<User> usersFromDB = mockedConnect.showUsers();
        List<User> expectedUser = new ArrayList<>();
        List<User> actualUsers = new ArrayList<>();
        usersFromDB.stream().filter(s -> s.getSurname().equals(surName)).forEach(expectedUser::add);
        actualUsers.add(user4);
        assertEquals(expectedUser, actualUsers);
    }

    @Test
    public void getPhoneNumberTabel() {
        List<User> userFromDB = mockedConnect.showUsers();
        User user = userFromDB.get(2);
        String expected = ("- Фамилия: " + user.getSurname() +
                " Имя: " + user.getName() + " Номер телефона: " + user.getPhoneNumber());
        String actual = "- Фамилия: Petrowa Имя: Jana Номер телефона: 9873215";
        Assert.assertEquals(expected, actual);
    }
}