package caseOpening.fileWriting;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import caseOpening.tools.StringComparator;
import caseOpening.tools.WeaponNameComparator;
import caseOpening.weapons.Weapons;


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
    public void addUser(HashMap<String, Object> user, String filepath){
        if(allreadyUser((String)user.get("username"), filepath)){
            throw new IllegalStateException();
        }
        String stringToWrite = "";
        for (final String keys : user.keySet()){
                //If object is an String Array (for example users weapons) sort them out before adding them to stringToWrite
                if(user.get(keys).getClass().equals(ArrayList.class)){
                    @SuppressWarnings("unchecked")
                    List<Weapons> values = new ArrayList<Weapons>((ArrayList<Weapons>)user.get(keys));
                    String stringToAdd = keys + ":";
                    values.sort(new WeaponNameComparator());
                    for (Weapons weapons : values){
                        stringToAdd += weapons.getName() + ",";
                    }
                    stringToWrite += stringToAdd + " ";

                } else {
                    stringToWrite += keys + ":" + user.get(keys) + " ";
                }
        }
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(stringToWrite);
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
            throw new IllegalArgumentException("Couldn't find user in " + filepath);
        }
    }

    /**
     * adds new information to an alrady existing user in UserOverview.txt
     */
    public void changeUser(String username, String type, String content, String filepath){
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
                    line = line.substring(0,changeIndex) + type + ":" + content + line.substring(line.indexOf(" ", changeIndex));
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
                throw new NullPointerException("User not found in " + filepath);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all user information from a spesific filepath. Note: only specified for one type of format
     */
    public Map<String, String> getUser(String username, String filePath){
        Map<String, String> returnMap = new Hashtable<>();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null){
                String usernameLine = line.substring(line.indexOf("username") + 9, line.indexOf(" ", line.indexOf("username")));
                if(usernameLine.equals(username)){
                    br.close();
                    line = line.trim();
                    String[] userInfo = line.split(" ");
                    for(String sections : userInfo){
                        String[] keyAndvalues = sections.split(":");
                        try{
                            returnMap.put(keyAndvalues[0], keyAndvalues[1]);
                        } catch (Exception e){
                            returnMap.put(keyAndvalues[0], "null");
                        }
                    }
                    return returnMap;
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
    public String getFromUser(String type, String username, String filePath){
        Map<String, String> user = getUser(username, filePath);
        return user.get(type);
    }

    
    /**
     * adds a weapon to a Users inventory
     */
    public void addWeapon(String username, Weapons weapon){
        String aquiredWeapons = getFromUser("weapons", username, "src/main/resources/caseOpening/UserOverview.txt");
        aquiredWeapons += weapon.getName() + ",";
        List<String> aquiredWeaponsList = Arrays.asList(aquiredWeapons.split(","));
        aquiredWeaponsList.sort(new StringComparator());
        aquiredWeapons = "";
        for (String s : aquiredWeaponsList){
            aquiredWeapons += s + ",";
        }
        changeUser(username, "weapons", aquiredWeapons, "src/main/resources/caseOpening/UserOverview.txt");
    }


    /**
     * removes a spesific weapon given that the User has that weapon
     */
    public void removeWeapon(String username, Weapons weapon){
        String aquiredWeapons = getFromUser("weapons", username, "src/main/resources/caseOpening/UserOverview.txt");
        aquiredWeapons = aquiredWeapons.replace(weapon.getName()+",", "");
        changeUser(username, "weapons", aquiredWeapons, "src/main/resources/caseOpening/UserOverview.txt");
    }

    /**
     * Takes in a {@code username} and sees if it is already in the file
     */
    private boolean allreadyUser(String username, String filepath){
        return getUser(username, filepath) != null;
    }

}
