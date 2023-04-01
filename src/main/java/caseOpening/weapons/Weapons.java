package caseOpening.weapons;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Weapons {
    protected int fireRate;
    protected int magazineSize;
    protected String name;
    protected String weaponImage;
    protected String rarity;
    protected int bullets;

    public Weapons(int fireRatePerSecond, int magazineSize, String name, String weaponImage, String rarity){
        if(fireRatePerSecond < 0 || magazineSize < 0){
            throw new IllegalArgumentException("No negative amount");
        }
        this.fireRate = fireRatePerSecond != 0 ? 1000/fireRatePerSecond : 0;
        this.magazineSize = magazineSize;
        this.bullets = magazineSize;
        this.name = name;
        this.weaponImage = weaponImage;
        this.rarity = rarity;
    }

    /**
     * Fires bullest with {@code fireRate} speed and stops when {@code magasinSize} is zero
     */
    public void fire(){
        if(this.bullets > 0){
            System.out.println("Fire Bullet " + (this.magazineSize-this.bullets));
            this.bullets--;
        } else{
            System.out.println("Out of bullets");
        }
    }
    
    /**
     * Reloads the bullets to the amount in {@code magasinSize}
     */
    public void reload(){
        this.bullets = this.magazineSize;
    }


    /*
     * Getter-methods
     */
    public Image getImage() throws IOException{
        //Gets bytes from image file and makes new image for imageView
        InputStream stream = new FileInputStream("./images/"+ this.weaponImage);
        Image wImage = new Image(stream);
        return wImage;
    }

    public String getName() {
        return this.name;
    }

    public int getFireRate() {
        return this.fireRate;
    }

    public int getMagazineSize() {
        return this.magazineSize;
    }

    public String getRarity() {
        return rarity;
    }
}
