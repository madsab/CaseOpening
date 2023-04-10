package caseOpening.tools;

import java.util.Comparator;

import caseOpening.weapons.Weapons;

public class WeaponRarityComparator implements Comparator<Weapons> {

    @Override
    public int compare(Weapons o1, Weapons o2) {
        int o1Value = rarityToValue(o1.getRarity());
        int o2Value = rarityToValue(o2.getRarity());
        if (o1Value > o2Value){
            return -1;
        } else if (o1Value == o2Value){
            return 0;
        } else {
            return 1;
        }

    }
    private int rarityToValue(String rarity){
        switch(rarity){
            case "legendary":
                return 5;
            case "epic":
                return 4;
            case "rare":
                return 3;
            case "uncommon":
                return 2;
            case "common":
                return 1;
        }
        return 0;
    }

    
    
}
