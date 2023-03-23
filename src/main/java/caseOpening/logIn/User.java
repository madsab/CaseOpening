package caseOpening.logIn;

import java.util.ArrayList;
import java.util.List;

import caseOpening.weapons.Weapons;

public class User {
    private String username, password;
    private int keys;
    private List<Weapons> aqquiredWeapons = new ArrayList<>();

    /*
     * Getter-methods
    */
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public int getKeys() {
        return keys;
    }

    public List<Weapons> getWeapons() {
        return aqquiredWeapons;
    }


    /*
     * Setter-methods
     */
    public void setPassword(String password) {
        if(!isValidPassword(password)){
            throw new IllegalArgumentException("Not valid password. Must contain at least on capital letter and a number");
        }
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addWeapon(Weapons weapon){
        this.aqquiredWeapons.add(weapon);
    }

    /*
     * Private validate methods
     */
    private boolean isValidPassword(String password){
     if(password.contains("[A-Z0-9]")){
        return true;
     }
     return false;
    }
}
