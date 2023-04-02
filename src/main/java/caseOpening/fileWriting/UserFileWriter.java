package caseOpening.fileWriting;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;


public class UserFileWriter implements Comparator<String> {
    
    public void writeToFile(Dictionary<String, String> user){
        try {
            FileWriter UserFile = new FileWriter("src/main/resources/caseOpening/UserOverview.txt");
            BufferedWriter fileWriter = new BufferedWriter(UserFile);
            fileWriter.write("Hall\n");
            fileWriter.close();
            UserFile.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addToUser(String username, String type, String content){

    }

    public Dictionary<String, String> getUser(String username){
        
    }

    public String getFromUser(String type){

    }

    @Override
    public int compare(String o1, String o2) {
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
    }

    public static void main(String[] args) {
        UserFileWriter test = new UserFileWriter();
        Dictionary<String, String> dict = new Hashtable<>();
        dict.put("username", "madsab");
        dict.put("usernames", "madsab");
        test.writeToFile(dict);
        test.writeToFile(dict);
    }
    
}
