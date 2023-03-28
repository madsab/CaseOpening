package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.List;

import caseOpening.weapons.Weapons;

public abstract class Case {
    protected List<Weapons> availableWeapons = new ArrayList<>();

    public Case(List<Weapons> availableWeapons){
        this.availableWeapons = availableWeapons;
    }

    public Weapons getPrizeWeapon(){
        int winningNumber = (int)Math.floor(Math.random()*1000+1);
        if(winningNumber < 10){
            return pickrandomWeapon("legendary");
        } else if (winningNumber < 75){
            return pickrandomWeapon("epic");
        } else if (winningNumber < 200){
            return pickrandomWeapon("rare");
        } else if (winningNumber < 500){
            return pickrandomWeapon("uncommon");
        } else {
            return pickrandomWeapon("common");
        }
    }

    private Weapons pickrandomWeapon(String rarity){
        List<Weapons> weaponsOfThisRarity = new ArrayList<>();
        //Add all weapons of a spesific rarity
        for (Weapons weapon : availableWeapons){
            if(weapon.getRarity().equals(rarity)){
                weaponsOfThisRarity.add(weapon);
            }
        }

        //Picks random weapon from weaponsOfThisRarity
        int choosenIndex = (int)Math.floor(Math.random()*weaponsOfThisRarity.size());
        try{
            return weaponsOfThisRarity.get(choosenIndex);
        } catch (Exception e){
            return null;
        }
    }
}
