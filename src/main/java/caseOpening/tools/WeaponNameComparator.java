package caseOpening.tools;

import java.util.Comparator;

import caseOpening.weapons.Weapons;

public class WeaponNameComparator implements Comparator<Weapons>{

    @Override
    public int compare(Weapons o1, Weapons o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
