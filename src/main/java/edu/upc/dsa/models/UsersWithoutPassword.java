package edu.upc.dsa.models;

public class UsersWithoutPassword {
    String name;
    String surname;
    String birthday;
    String email;

    public UsersWithoutPassword(){};

    public UsersWithoutPassword(String name, String surname, String birthday, String email){
        this.name=name;
        this.surname=surname;
        this.birthday =birthday;
        this.email=email;


        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setEmail(email);


    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
