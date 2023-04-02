package caseOpening.fileWriting;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;


public class UserFileWriterReader implements Comparator<String> {
    
    /**
     * adds User to UserOverview.txt. Must be a type Map with values username, password, keys and aqquired weapons
     */
    public void addUser(Map<String, String> user){
        String stringToWrite = "";
        for (final String keys : user.keySet()){
                stringToWrite += keys + ":" + user.get(keys) + " ";
        }
        try {
            FileWriter fw = new FileWriter("src/main/resources/caseOpening/UserOverview.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(stringToWrite);
            pw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * adds new information to an alrady existing user in UserOverview.txt
     */
    public void addToUser(String username, String type, String content){
        String lineToChange = "";
        //Finds user in UserOverview.txt
        try {
            FileReader fw = new FileReader("src/main/resources/caseOpening/UserOverview.txt");
            BufferedReader bw = new BufferedReader(fw);
            String line = bw.readLine();

            while (line != null){
                if(line.contains(username)){
                    lineToChange = line;
                }
                line = bw.readLine();
            }
            //changes the line
            int changeIndex = lineToChange.indexOf(type);
            lineToChange = lineToChange.substring(0,changeIndex) + " " + type + ":" + content + lineToChange.substring(changeIndex + (2 + type.length() + content.length()));
            bw.close();

            //Writes it back to the right position
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getUser(String username){
        
    }

    public String getFromUser(String type, String username){

    }

    @Override
    public int compare(String o1, String o2) {
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
    }

    public static void main(String[] args) {
        UserFileWriterReader test = new UserFileWriterReader();
        Map<String, String> dict = new Hashtable<>();
    }
    
}
