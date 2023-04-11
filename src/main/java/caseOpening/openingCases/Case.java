package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.List;

import caseOpening.weapons.Weapons;

public abstract class Case {
    protected List<Weapons> availableWeapons = new ArrayList<>();
    protected List<Weapons> availableKnifes = new ArrayList<>();

    public Case(List<Weapons> availableWeapons, List<Weapons> availableKnifes){
        for (Weapons weapon : availableWeapons){
            this.availableWeapons.add(weapon);
        }
        for (Weapons weapon : availableKnifes){
            this.availableKnifes.add(weapon);
        }
    }

    public Weapons getPrizeWeapon(){
        int winningNumber = (int)Math.floor(Math.random()*1000+1);
        if(winningNumber < 50){
            return pickrandomWeapon("legendary");
        } else if (winningNumber < 175){
            return pickrandomWeapon("epic");
        } else if (winningNumber < 300){
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

    public Weapons pickRandomKnife(){
        int choosenIndex = (int)Math.floor(Math.random()*availableKnifes.size());
        return availableKnifes.get(choosenIndex);
    }
}
