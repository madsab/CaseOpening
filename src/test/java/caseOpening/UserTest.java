package caseOpening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import caseOpening.logIn.User;
import caseOpening.weapons.Weapon;

public class UserTest {
    
    private User validUser = new User("TestUser", "Passord123");
    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {new User("Ole", "ugyldigPassord");}, "Should be an invalid password");
        assertThrows(IllegalArgumentException.class, () -> {new User(null, null);}, "Can't be null");
        assertEquals("TestUser",validUser.getUsername(), "Username should be the username input");
        assertEquals("Passord123",validUser.getPassword(), "Password should be password input");
        assertEquals(5,validUser.getKeys(), "Keys should be start value. 5 in this case");
        assertTrue(validUser.getWeapons().isEmpty(), "Weapons should be empty");
    }
    
    @Test
    public void testSetUsername(){
        validUser.setUsername("Test");
        assertEquals("Test", validUser.getUsername(), "Should be valid username");
        validUser.setUsername("TestUser123");
        assertEquals("TestUser123", validUser.getUsername(), "Numbers should be valid in username");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setUsername("Test?");}, "Can't have special characters");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setUsername("Test User");}, "Can't have whitespace");
        
    }
    
    @Test
    public void testSetPassword(){
        validUser.setPassword("Test123");
        assertEquals("Test123", validUser.getPassword(), "Should be valid password");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setPassword("taper123");}, "Must have one capital letter");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setPassword("Taper");}, "Must have one number");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setPassword("Taper 123");}, "Can't have whitespace");
    }

    @Test
    public void testSetKeys(){
        validUser.setKeys(10);
        assertEquals(10, validUser.getKeys(), "Should be 10 keys");
        assertThrows(IllegalArgumentException.class, () -> {validUser.setKeys(-10);}, "Cant set negative keys");
    } 
    @Test
    public void testAddWeapon() {
        Weapon weapon = new Weapon("WeaponName", "WeaponImage", "Rarity");
        validUser.addWeapon(weapon);
        assertTrue(validUser.getWeapons().contains(weapon), "User weapons should contain added weapon");
    }

    @Test
    public void testRemoveWeapon() {
        Weapon weapon = new Weapon("WeaponName", "WeaponImage", "Rarity");
        validUser.addWeapon(weapon);
        validUser.removeWeapon(weapon);
        assertFalse(validUser.getWeapons().contains(weapon), "User weapons should not contain the removed weapon");
    }

    @Test
    public void testAddKeys() {
        validUser.addKeys(10);
        assertEquals(15, validUser.getKeys(), "Should be 15 keys after adding 10");
        assertThrows(IllegalArgumentException.class, () -> {
            validUser.addKeys(-10);
        }, "Should not add negative amounts of keys");
    }

    @Test
    public void testRemoveKeys() {
        assertThrows(IllegalStateException.class, () -> validUser.removeKeys(10), "Should not be able to remove more keys than what the user has");
        validUser.removeKeys(5);
        assertEquals(0, validUser.getKeys(), "User keys should be 0 after removing 5");
    }

}

