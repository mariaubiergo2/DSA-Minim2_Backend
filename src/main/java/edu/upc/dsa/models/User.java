package edu.upc.dsa.models;


import edu.upc.dsa.exceptions.IncorrectCredentialsException;
import edu.upc.dsa.exceptions.NotEnoughMoneyException;
import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    String idUser;
    String name;
    String surname;
    String birthday;
    String email;
    String password;
    Boolean currentlyPlaying;
    Integer coins;
    Integer experience;


    public User(){};
    public User(String name, String surname,String date, String email, String password) {
        this.idUser = RandomUtils.getId();
        this.name = name;
        this.surname = surname;
        this.birthday = date;
        this.email = email;
        this.password = password;
        this.currentlyPlaying = false;
        this.coins = 50;
        this.experience = 0;

    }

    public String getName() {
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getBirthday(){   return this.birthday;}

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){ return this.password; }

    public Boolean getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public Integer getCoins() {
        return coins;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setCurrentlyPlaying(Boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public void purchaseGadget(Gadget gadget) throws NotEnoughMoneyException {
        if(gadget.getCost()>this.coins){
            throw new NotEnoughMoneyException();
        }

        this.coins = this.coins - gadget.getCost();
    }

    public Boolean validCredentials(Credentials credentials) {
        return Objects.equals(this.email, credentials.getEmail()) && Objects.equals(this.password, credentials.getPassword());
    }
}
