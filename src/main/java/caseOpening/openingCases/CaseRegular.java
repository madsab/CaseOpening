package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import caseOpening.weapons.Weapons;

public class CaseRegular extends Case {
    private static List<Weapons> availableWeapons = new ArrayList<>(Arrays.asList(
        new Weapons("SwitchBladeGolden", "weapons-SpecialItem.jpg", "legendary"),
        new Weapons("AK47Rebel", "weapons-AK47Rebel.jpg", "uncommon"),
        new Weapons("AR15Tagged", "weapons-AR15Tagged.jpg", "common"),
        new Weapons("SMGDesertDeath", "weapons-SMGDesertDeath.jpg", "rare"),
        new Weapons("SMG", "weapons-SMG.jpg", "common"),
        new Weapons("PistolNightSky", "weapons-PistolNightSky.jpg", "rare"),
        new Weapons("PistolSilencedBlueBoy", "weapons-PistolSilencedBlueBoy.jpg", "rare"),
        new Weapons("SniperBlueFire", "weapons-SniperBlueFire.jpg", "epic"),
        new Weapons("SniperMSRTiger", "weapons-SniperMSRTiger.jpg", "uncommon")
        )); 
    
    public CaseRegular() {
        super(availableWeapons);

    }
}  
    