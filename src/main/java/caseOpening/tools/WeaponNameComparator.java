package caseOpening.tools;

import java.util.Comparator;

import caseOpening.weapons.Weapon;

public class WeaponNameComparator implements Comparator<Weapon>{

    @Override
    public int compare(Weapon o1, Weapon o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
