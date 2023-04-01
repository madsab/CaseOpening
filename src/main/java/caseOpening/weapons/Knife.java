package caseOpening.weapons;

public class Knife extends Weapons{

    public Knife(String name, String weaponImage, String rarity) {
        super(0, 0, name, weaponImage, rarity);
    }
    @Override
    public void fire() {
        System.out.println("Sliing");
    }

    @Override
    public void reload() {
        System.out.println("Flip animation");
    }
    
}
