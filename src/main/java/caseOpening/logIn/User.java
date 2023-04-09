package caseOpening.logIn;

import java.util.ArrayList;
import java.util.List;

import caseOpening.fileWriting.UserFileWriterReader;
import caseOpening.weapons.Weapons;

public class User {
    private String username, password;
    private int keys;
    private List<Weapons> aqquiredWeapons = new ArrayList<>();
    private UserFileWriterReader fw = new UserFileWriterReader();
    private String filePath;

    /**
     * Constructor for making a new User
     */
    public User(String username, String password){
        if(username == null || password == null){
            throw new IllegalArgumentException("Brukernavn/Passord kan ikke være null");
        }
        this.setUsername(username);
        this.setPassword(password);
        this.keys = 5;
    }

    /**
     * Constructor for making a User instance of an allready existing user in a {@code filePath}
     */
    public User(String existingUsername , String filePath, boolean allreadyCreated){
        this.filePath = filePath;
        if(allreadyCreated){
            this.setUsername(existingUsername);
            this.setPassword((String)fw.getFromUser("password", existingUsername, filePath));
            this.keys = (Integer)fw.getFromUser("keys", existingUsername, filePath);
            @SuppressWarnings("unchecked")
            List<Weapons> weapons = ((List<Weapons>)fw.getFromUser("weapons", existingUsername, filePath));
            for (Weapons weapon : weapons){
                this.aqquiredWeapons.add(weapon);
            }

            

        }
    }

    /**
     * Constructor that gets the actvive User from Active.txt {@code filePath} is for where you also want to update info when changed
     * <p>
     * Usefull for storing an active user
     */
    public User(String filePath){
        this.filePath = filePath;
        this.setUsername(fw.getUserNameFromLine(0, "src/main/resources/caseOpening/ActiveUser.txt"));
        this.setPassword((String)fw.getFromUser("password", this.username, filePath));
        this.keys = (Integer)fw.getFromUser("keys", this.getUsername(), filePath);
        @SuppressWarnings("unchecked")
        List<Weapons> weapons = ((List<Weapons>)fw.getFromUser("weapons", this.getUsername(), filePath));
            for (Weapons weapon : weapons){
                this.aqquiredWeapons.add(weapon);
            }
    }

    public User(String username, String password, int keys, List<Weapons> weapons){
        this.setUsername(username);
        this.setPassword(password);
        this.setKeys(keys);
        for (Weapons weapon : weapons){
            this.aqquiredWeapons.add(weapon);
        }
    }

    public void addUserToFile(String filePath){
        this.filePath = filePath;
        fw.addUser(this, filePath);
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

    public Object get(String type){
        switch(type){
            case "password":
                return this.getPassword();
            case "username":
                return this.getUsername();
            case "keys":
                return this.getKeys();
            case "weapons":
                return this.getWeapons();
        }
        return null;
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

    public void setKeys(int number){
        if(number < 0){
            throw new IllegalArgumentException("Number can't be negative");
        }
        this.keys = number;
    }


    public void addWeapon(Weapons weapon){
        this.aqquiredWeapons.add(weapon);
        fw.addWeapon(this.getUsername(), weapon, this.filePath);
        fw.addWeapon(this.getUsername(), weapon, "src/main/resources/caseOpening/ActiveUser.txt");
    }

    public void addKeys(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Can't be negative amount");
        }
        this.keys += amount;
        fw.changeUser(this.getUsername(), "keys", String.valueOf(this.getKeys()), this.filePath);
        fw.changeUser(this.getUsername(), "keys", String.valueOf(this.getKeys()), "src/main/resources/caseOpening/ActiveUser.txt");
    }

    public void removeKeys(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Can't have negative amount");
        }
        else if (this.keys - amount < 0){
            throw new IllegalStateException("Can't remove more keys than you have");
        }
        this.keys -= amount;
        fw.changeUser(this.getUsername(), "keys", String.valueOf(this.getKeys()), this.filePath);
        fw.changeUser(this.getUsername(), "keys", String.valueOf(this.getKeys()), "src/main/resources/caseOpening/ActiveUser.txt");
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
        return !username.matches("^.*['§*!#$%&/()=?+]");
    }

    @Override
    public String toString() {
        String weaponString = "";
        for (Weapons weapon : aqquiredWeapons){
            weaponString += weapon.getName() + "(" + weapon.getRarity() + "),";
        }
        return "username:" + this.username + " password:"+this.password + " keys:" + this.keys + " weapons:" + weaponString;
    }

}
