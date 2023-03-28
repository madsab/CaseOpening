package caseOpening.weapons;

import java.util.concurrent.TimeUnit;



public class AutomaticWeapons extends Weapons {
    public AutomaticWeapons(int fireRatePerSecond, int magazineSize, String camo, String weaponImage) {
        super(fireRatePerSecond, magazineSize, camo, weaponImage);
    }
    

    public void autoFire(){
        while (this.bullets > 0){
            System.out.println("Fire Bullet " + (magazineSize-bullets));
            try {
                TimeUnit.MILLISECONDS.sleep(this.fireRate);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            this.bullets--;
        }
        System.out.println("Out of bullets");
    }
    
}
