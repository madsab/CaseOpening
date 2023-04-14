package caseOpening.openingCases;

import java.util.ArrayList;
import java.util.List;

import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapon;

public abstract class Case {
    protected List<Weapon> availableWeapons = new ArrayList<>();
    protected List<Knife> availableKnifes = new ArrayList<>();

    public Case(List<Weapon> availableWeapons, List<Knife> availableKnifes){
        for (Weapon weapon : availableWeapons){
            this.availableWeapons.add(weapon);
        }
        for (Knife weapon : availableKnifes){
            this.availableKnifes.add(weapon);
        }
    }

    public Weapon getPrizeWeapon(){
        int winningNumber = (int)Math.floor(Math.random()*1000+1);
        if(winningNumber < 75){
            return pickrandomWeapon("legendary");
        } else if (winningNumber < 200){
            return pickrandomWeapon("epic");
        } else if (winningNumber < 300){
            return pickrandomWeapon("rare");
        } else if (winningNumber < 600){
            return pickrandomWeapon("uncommon");
        } else {
            return pickrandomWeapon("common");
        }
    }

    private Weapon pickrandomWeapon(String rarity){
        List<Weapon> weaponsOfThisRarity = new ArrayList<>();
        //Add all weapons of a spesific rarity
        for (Weapon weapon : availableWeapons){
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

    public Weapon pickRandomKnife(){
        int choosenIndex = (int)Math.floor(Math.random()*availableKnifes.size());
        return availableKnifes.get(choosenIndex);
    }
}
