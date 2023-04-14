package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapon;

public class CaseRegular extends Case {
    private static List<Weapon> availableWeapons = new ArrayList<>(Arrays.asList(
        new Weapon("SpecialItem", "weapons-SpecialItem.jpg", "legendary"),
        new Weapon("AK47Rebel", "weapons-AK47Rebel.jpg", "uncommon"),
        new Weapon("M4A1Tagged", "weapons-M4A1Tagged.jpg", "common"),
        new Weapon("SMGDesertDeath", "weapons-SMGDesertDeath.jpg", "rare"),
        new Weapon("SMG", "weapons-SMG.jpg", "common"),
        new Weapon("PistolNightSky", "weapons-PistolNightSky.jpg", "rare"),
        new Weapon("PistolSilencedBlueBoy", "weapons-PistolSilencedBlueBoy.jpg", "rare"),
        new Weapon("SniperBlueFire", "weapons-SniperBlueFire.jpg", "epic"),
        new Weapon("SniperMSRTiger", "weapons-SniperMSRTiger.jpg", "uncommon")
        )); 
    private static List<Knife> availableKnifes = new ArrayList<>(Arrays.asList(
        new Knife("BlackHawk", "weapons-BlawkHawk.jpg", "legendary"),
        new Knife("RedVelvet", "weapons-RedVelvet.jpg", "legendary"),
        new Knife("GoldenViking", "weapons-GoldenViking.jpg", "legendary"),
        new Knife("DeadSteel", "weapons-DeadSteel.jpg", "legendary"),
        new Knife("BloodMoney", "weapons-BloodMoney.jpg", "legendary"),
        new Knife("SwiftSwirl", "weapons-SwiftSwirl.jpg", "legendary")
    ));
    
    public CaseRegular() {
        super(availableWeapons, availableKnifes);

    }
}  
    