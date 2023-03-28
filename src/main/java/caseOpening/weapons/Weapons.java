package caseOpening.weapons;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Weapons {
    protected int fireRate;
    protected int magazineSize;
    protected String camo;
    protected String weaponImage;
    protected int bullets;

    public Weapons(int fireRatePerSecond, int magazineSize, String camo, String weaponImage){
        if(fireRatePerSecond <= 0 || magazineSize <= 0){
            throw new IllegalArgumentException("No negative amount");
        }
        this.fireRate = 1000/fireRatePerSecond;
        this.magazineSize = magazineSize;
        this.bullets = magazineSize;
        this.camo = camo;
        this.weaponImage = weaponImage;
    }

    public void fire(){
        if(bullets > 0){
            System.out.println("Fire Bullet " + (magazineSize-bullets));
            bullets--;
        } else{
            System.out.println("Out of bullets");
        }
    }
    
    public void reload(){
        this.bullets = this.magazineSize;
    }


    /*
     * Getter-methods
     */
    public Image getImage() throws IOException{
        //Gets bytes from image file and makes new image for imageView
        InputStream stream = new FileInputStream("file:/Users/lottekvalheim/Documents/MADS/Prosjekt_Java/CaseOpening/images/" + weaponImage);
        Image wImage = new Image(stream);
        return wImage;
    }

    public String getCamo() {
        return camo;
    }

    public int getFireRate() {
        return fireRate;
    }

    public int getMagazineSize() {
        return magazineSize;
    }
}
