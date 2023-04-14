package caseOpening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caseOpening.fileWriting.UserFileWriterReader;
import caseOpening.logIn.User;
import caseOpening.weapons.Weapon;

public class UserFileWriterReaderTest {
    private UserFileWriterReader fileReaderWriter = new UserFileWriterReader();
    private String testFilepath = "src/test/java/caseOpening/testFile.txt";
    private User testUser = new User("TestUser", "Testuser123");

    @BeforeEach
    public void clearFile(){
        fileReaderWriter.overrideFile("", testFilepath);
    }

    @Test
    public void testGetUser(){
        fileReaderWriter.addUser(testUser, testFilepath);
        assertEquals(testUser.getKeys(), fileReaderWriter.getUser("TestUser", testFilepath).getKeys(), "Keys should be equal");
        assertEquals(testUser.getUsername(), fileReaderWriter.getUser("TestUser", testFilepath).getUsername(), "Username should be equal");
        assertEquals(testUser.getPassword(), fileReaderWriter.getUser("TestUser", testFilepath).getPassword(), "Password should be equal");
    }

    @Test
    public void testAddUser(){
        fileReaderWriter.addUser(testUser, testFilepath);
        assertEquals(testUser.getKeys(), fileReaderWriter.getUser("TestUser", testFilepath).getKeys(), "Keys should be equal");
        assertEquals(testUser.getUsername(), fileReaderWriter.getUser("TestUser", testFilepath).getUsername(), "Username should be equal");
        assertEquals(testUser.getPassword(), fileReaderWriter.getUser("TestUser", testFilepath).getPassword(), "Password should be equal");
    }
    
    @Test
    public void testGetFromUser(){
        Weapon testWeapon = new Weapon("AK-47", "weapons-AK47Rebel.jpg", "legendary");
        fileReaderWriter.addUser(testUser, testFilepath);
        List<Weapon> userWeapons = new ArrayList<>();
        userWeapons.add(testWeapon);
        fileReaderWriter.addWeapon(testUser.getUsername(), testWeapon, testFilepath);
        assertEquals("TestUser", fileReaderWriter.getFromUser("username", testUser.getUsername(), testFilepath));
        assertEquals("Testuser123", fileReaderWriter.getFromUser("password", testUser.getUsername(), testFilepath));
        assertEquals(5, fileReaderWriter.getFromUser("keys", testUser.getUsername(), testFilepath));
        assertEquals(userWeapons.get(0).getName(), ((List<Weapon>)fileReaderWriter.getFromUser("weapons", testUser.getUsername(), testFilepath)).get(0).getName());
    }

    @Test
    public void testChangeUser(){
        fileReaderWriter.addUser(testUser, testFilepath);
        //Test change of invalid type
        assertThrows(IllegalArgumentException.class, () -> {fileReaderWriter.changeUser(testUser.getUsername(), "money", "35 000", testFilepath);}, "Should throw on invalid type");
        assertThrows(NullPointerException.class, () -> {fileReaderWriter.changeUser("NotAUser", "username", "Billy", testFilepath);}, "Should not find user");

        
        //Test change of password
        assertEquals("Testuser123", fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getPassword());
        assertThrows(IllegalArgumentException.class , () -> {fileReaderWriter.changeUser(testUser.getUsername(), "password", "abc", testFilepath);}, "Should throw on invalid password");
        fileReaderWriter.changeUser(testUser.getUsername(), "password", "TestUser1234",testFilepath);
        assertEquals("TestUser1234", fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getPassword());
        
        //Test change of keys
        assertEquals(5, fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getKeys());
        assertThrows(IllegalArgumentException.class , () -> {fileReaderWriter.changeUser(testUser.getUsername(), "keys", "-5", testFilepath);}, "Should throw on invalid password");
        fileReaderWriter.changeUser(testUser.getUsername(), "keys", "25", testFilepath);
        assertEquals(25, fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getKeys());
        
        //Test change of weapons
        Weapon testWeapon = new Weapon("AK47", "Ak4.jpg", "rare");
        assertEquals(true, fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getWeapons().isEmpty(), "Weapons should be empty on start");
        fileReaderWriter.changeUser(testUser.getUsername(), "weapons", testWeapon.toString(), testFilepath);
        assertEquals(testWeapon.getName(), fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getWeapons().get(0).getName(), "Weapons should be only AK47");

        //Test change of username (Is last because it changes the username of testUser)
        assertEquals("TestUser", fileReaderWriter.getUser(testUser.getUsername(), testFilepath).getUsername());
        assertThrows(IllegalArgumentException.class , () -> {fileReaderWriter.changeUser(testUser.getUsername(), "username", "&#/$78", testFilepath);}, "Should throw on invalid username");
        fileReaderWriter.changeUser(testUser.getUsername(), "username", "NotTestUser", testFilepath);
        assertEquals("NotTestUser", fileReaderWriter.getUser("NotTestUser", testFilepath).getUsername());
 
    }
}

    
