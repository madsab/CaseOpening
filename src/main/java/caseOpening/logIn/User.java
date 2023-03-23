package caseOpening.logIn;

import java.util.ArrayList;
import java.util.List;


import caseOpening.weapons.Weapons;

public class User {
    private String username, password;
    private int keys;
    private List<Weapons> aqquiredWeapons = new ArrayList<>();

    /*
     * Constructors
     */
    public User(String username, String password){
        if(username == null || password == null){
            throw new IllegalArgumentException("Brukernavn/Passord kan ikke være null");
        }
        this.setUsername(username);
        this.setPassword(password);
    }

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
        if(!isValidUsername(username)){
            throw new IllegalArgumentException("Not valid username. No symbols or digits");
        }
        this.username = username;
    }

    public void addWeapon(Weapons weapon){
        this.aqquiredWeapons.add(weapon);
    }

    public void addKeys(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Can't be negative amount");
        }
        this.keys += amount;
    }

    public void removeKeys(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Can't have negative amount");
        }
        else if (this.keys - amount < 0){
            throw new IllegalStateException("Can't remove more keys than you have");
        }
        this.keys -= amount;
    }

    /*
     * Private validate methods
     */
    private boolean isValidPassword(String password){
        // password contains at least one capital letter and one number
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        
    }

    private boolean isValidUsername(String username){
        //Check if username contains any of this special characters or numbers
        return !username.matches("^.*['§*!#$%&/()=?+0-9]");
    }

}
