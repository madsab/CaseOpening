package java.profile;

public class User {
    private String username;
    private String password;
    private int keys;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public int getKeys() {
        return keys;
    }
}
