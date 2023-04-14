package caseOpening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import caseOpening.weapons.Weapon;

public class WeaponsTest {
    
    private Weapon validWeapon = new Weapon("AK-47", "weapons-AK47Rebel.jpg", "legendary");
    @Test
    public void testConstructor(){
        Weapon weapon = new Weapon("AK-47", "weapons-AK47Rebel.jpg", "legendary");
        assertEquals("AK-47", weapon.getName(), "Name should be AK-47");
        assertEquals("legendary", weapon.getRarity(), "Should return legendary");
        assertNotNull(weapon.getImage());
        assertThrows(NullPointerException.class, () -> {new Weapon(null, null, null);}, "Invalid values");
    }

    @Test
    public void testSetValue() {
        validWeapon.setValue(99);
        assertEquals(99, validWeapon.getValue(), "Value should be 99");
        assertThrows(IllegalArgumentException.class, () -> {validWeapon.setValue(-10);},"Should not set negative values");
    }
}
