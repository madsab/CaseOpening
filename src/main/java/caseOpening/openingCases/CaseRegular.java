package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapons;

public class CaseRegular extends Case {
    private static List<Weapons> availableWeapons = new ArrayList<>(Arrays.asList(
        new Weapons("SpecialItem", "weapons-SpecialItem.jpg", "legendary"),
        new Weapons("AK47Rebel", "weapons-AK47Rebel.jpg", "uncommon"),
        new Weapons("M4A1Tagged", "weapons-M4A1Tagged.jpg", "common"),
        new Weapons("SMGDesertDeath", "weapons-SMGDesertDeath.jpg", "rare"),
        new Weapons("SMG", "weapons-SMG.jpg", "common"),
        new Weapons("PistolNightSky", "weapons-PistolNightSky.jpg", "rare"),
        new Weapons("PistolSilencedBlueBoy", "weapons-PistolSilencedBlueBoy.jpg", "rare"),
        new Weapons("SniperBlueFire", "weapons-SniperBlueFire.jpg", "epic"),
        new Weapons("SniperMSRTiger", "weapons-SniperMSRTiger.jpg", "uncommon")
        )); 
    private static List<Weapons> availableKnifes = new ArrayList<>(Arrays.asList(
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
    