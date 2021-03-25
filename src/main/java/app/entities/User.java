package app.entities;

public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;

    @Override
    public String toString() {
        return "- Пользователь №" + getId() + ", " + getSurname() + " " + getName() + ", возраст: "
                + getAge() + ", № телефона: " + getPhoneNumber() + ".";
    }

    public User(int id, String name, String surname, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
