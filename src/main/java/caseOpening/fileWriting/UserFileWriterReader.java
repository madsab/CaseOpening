package caseOpening.fileWriting;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import caseOpening.logIn.User;
import caseOpening.tools.WeaponNameComparator;
import caseOpening.weapons.Weapon;


public class UserFileWriterReader {
    private boolean hasChanged;
    /**
     * Write a {@code string} to a spesific file. Does not add lines, only rewrites
     */
    public void overrideFile(String string, String filepath){
        try {
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(string);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * adds User to UserOverview.txt. Must be a type Map with values username, password, keys and aqquired weapons
     */
    public void addUser(User user, String filepath){
        if(allreadyUser(user.getUsername(), filepath)){
            throw new IllegalStateException();
        }
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(user.toString());
            pw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete a user in a file
     */
    public void deleteUser(String username, String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringBuffer sb = new StringBuffer();

            while (line != null){
                String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
                if(usernameLine.equals(username)){
                    line = br.readLine();
                } else {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
            }
            overrideFile(sb.toString(), filepath);
            br.close();

        } catch (Exception e){
            throw new IllegalArgumentException("Couldn't find "+ username + " in " + filepath);
        }
    }

    /**
     * adds new information to an alrady existing user in UserOverview.txt
     */
    public void changeUser(String username, String type, String content, String filepath){
        User validatorUser = new User(username, filepath, true);
        switch(type){
            case "username":
                validatorUser.setUsername(content);
                break;
            case "password":
                validatorUser.setPassword(content);
                break;
            case "keys":
                validatorUser.setKeys(Integer.parseInt(content));
                break;
            case "weapons":
                break;
            default:
                throw new IllegalArgumentException("Not a type");
        }
        //Finds user in UserOverview.txt
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringBuffer sb = new StringBuffer();
            //String buffer adds all previous lines, but changed the desired line
            while (line != null){
                String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
                if(usernameLine.equals(username)){
                    int changeIndex = line.indexOf(type);
                    try{
                        line = line.substring(0,changeIndex+type.length()+1) + content + line.substring(line.indexOf(" ", changeIndex));
                    } catch(IndexOutOfBoundsException e){
                        line = line.substring(0,changeIndex+type.length()+1) + content;
                    }
                    hasChanged = true;
                }
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            if(hasChanged){
                String updatedOverview = sb.toString();
                overrideFile(updatedOverview, filepath);
            } else {
                throw new IllegalArgumentException("Couldn't find user or change the type");
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all user information from a spesific filepath. Note: only specified for one type of format
     */
    public User getUser(String username, String filePath){
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null){
                String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
                if(usernameLine.equals(username.trim())){
                    String password = line.substring(line.indexOf("password")+ 9, line.indexOf(" ", line.indexOf("password"))); 
                    int keys = Integer.parseInt(line.substring(line.indexOf("keys")+ 5, line.indexOf(" ", line.indexOf("keys"))));
                    List<Weapon> returnWeapons = new ArrayList<>();
                    try {
                        String[] weapons = line.substring(line.indexOf("weapons") + 8).split(",");                        
                        for(String weapon : weapons){
                            String[] s = weapon.replace(")", "").split("\\(");
                            returnWeapons.add(new Weapon(s[0], "weapons-" + s[0] + ".jpg", s[1]));
                        } 
                    } catch (Exception e){
                        
                    }
                    br.close();
                    return new User(username, password, keys, returnWeapons);
                }
                line = br.readLine();
            }
            br.close();
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserAsString(String username, String filePath){
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null){
                String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
                if(usernameLine.equals(username)){
                    br.close();
                    return line;
                }
                line = br.readLine();
            }
            br.close();
            return null;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserNameFromLine(int lineNumber, String filePath){
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int counter = 0;
            while (counter < lineNumber){
                line = br.readLine();
            }
            String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
            br.close();
            return usernameLine;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a spesfic category from the user
     */
    public Object getFromUser(String type, String username, String filePath){
        User user = getUser(username, filePath);
        return user.get(type);
    }

    
    /**
     * adds a weapon to a Users inventory, sorts by weapon Name
     */
    public void addWeapon(String username, Weapon weapon, String filePath){
        @SuppressWarnings("unchecked")
        List<Weapon> aquiredWeapons = (List<Weapon>)this.getFromUser("weapons", username, filePath);
        aquiredWeapons.add(weapon);
        aquiredWeapons.sort(new WeaponNameComparator());
        String weaponsAsString = "";
        for( Weapon aquiredWeapon : aquiredWeapons){
            weaponsAsString += aquiredWeapon.getName() + "(" + aquiredWeapon.getRarity() + "),";
        }
        changeUser(username, "weapons", weaponsAsString, filePath);
    }


    /**
     * removes a spesific weapon given that the User has that weapon
     */
    public void removeWeapon(String username, Weapon weapon, String filePath){
        @SuppressWarnings("unchecked")
        List<Weapon> aquiredWeapons = (List<Weapon>)getFromUser("weapons", username, filePath);
        aquiredWeapons.sort(new WeaponNameComparator());
        String weaponsAsString = "";
        boolean allreadyRemoved = false;
        for( Weapon aquiredWeapon : aquiredWeapons){
            if(!aquiredWeapon.getName().equals(weapon.getName()) || allreadyRemoved){
                weaponsAsString += aquiredWeapon.getName() + "(" + aquiredWeapon.getRarity() + "),";
            } else {
                allreadyRemoved = true;
            }
        }
        changeUser(username, "weapons", weaponsAsString, filePath);
    }

    /**
     * Takes in a {@code username} and sees if it is already in the file
     */
    private boolean allreadyUser(String username, String filepath){
        return getUser(username, filepath) != null;
    }

}
