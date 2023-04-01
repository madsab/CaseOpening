package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import caseOpening.weapons.AutomaticWeapons;
import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapons;

public class CaseRegular extends Case {
    private static List<Weapons> availableWeapons = new ArrayList<>(Arrays.asList(
        new Knife("SwitchBladeGolden", "weapons-SpecialItem.jpg", "legendary"),
        new AutomaticWeapons(10, 30, "AK47Rebel", "weapons-AK47Rebel.jpg", "uncommon"),
        new AutomaticWeapons(13, 30, "AR15Tagged", "weapons-AR15Tagged.jpg", "common"),
        new AutomaticWeapons(11, 35, "SMGDesertDeath", "weapons-SMGDesertDeath.jpg", "rare"),
        new AutomaticWeapons(11, 35, "SMG", "weapons-SMG.jpg", "common"),
        new Weapons(2, 10, "PistolNightSky", "weapons-PistolNightSky.jpg", "rare"),
        new Weapons(2, 10, "PistolSilencedBlueBoy", "weapons-PistolSilencedBlueBoy.jpg", "rare"),
        new Weapons(1, 6, "SniperBlueFire", "weapons-SniperBlueFire.jpg", "epic"),
        new Weapons(1, 6, "SniperMSRTiger", "weapons-SniperMSRTiger.jpg", "uncommon")
        )); 
    
    public CaseRegular() {
        super(availableWeapons);

    }
}  
    