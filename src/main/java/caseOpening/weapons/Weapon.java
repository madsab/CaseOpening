package caseOpening.weapons;


import java.io.FileInputStream;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Weapon {
    protected String name;
    protected String weaponImage;
    protected String rarity;
    protected int valueInKeys;

    public Weapon(String name, String weaponImage, String rarity){
        this.name = name;
        this.weaponImage = weaponImage;
        this.rarity = rarity;
        setValue(rarity);
    }

    /*
     * Getter-methods
     */
    public Image getImage(){
        //Gets bytes from image file and makes new image for imageView
        try{
            InputStream stream = new FileInputStream("./images/"+ this.weaponImage);
            Image wImage = new Image(stream);
            return wImage;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getRarity() {
        return rarity;
    }

    public int getValue(){
        return this.valueInKeys;
    }

    public void setValue(String rarity){
        switch(rarity){
            case "legendary":
                this.valueInKeys = 169;
                return;
            case "epic":
                this.valueInKeys = 69;
                return;
            case "rare":
                this.valueInKeys = 25;
                return;
            case "uncommon":
                this.valueInKeys = 2;
                return;
            case "common":
                this.valueInKeys = 1;
                return;
        }
    }

    public void setValue(int value){
        if(value < 0){
            throw new IllegalArgumentException("No negative value");
        }
        this.valueInKeys = value;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.rarity + ")";
    }
}
