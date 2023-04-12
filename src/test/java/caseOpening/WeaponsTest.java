package caseOpening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import caseOpening.weapons.Weapons;

public class WeaponsTest {
    
    @Test
    public void testConstructor(){
        Weapons weapon = new Weapons("AK-47", "weapons-AK47Rebel.jpg", "legendary");
        assertEquals("AK-47", weapon.getName(), "Name should be AK-47");
        assertEquals("legendary", weapon.getRarity(), "Should return legendary");
        assertNotNull(weapon.getImage());
    }

    @Test
    public void testSetValue() {
        Weapons weapon = new Weapons("Desert Eagle", "deagle.png", "epic");
        weapon.setValue(99);
        assertEquals(99, weapon.getValue());
    }
}
