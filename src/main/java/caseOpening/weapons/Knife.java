package caseOpening.weapons;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Knife extends Weapon{

    public Knife(String name, String weaponImage, String rarity) {
        super(name, weaponImage, rarity);
    }

    public Image getHidingImage(){
        //Gets bytes from image file and makes new image for imageView
        try{
            InputStream stream = new FileInputStream("./images/weapons-SpecialItem.jpg");
            Image Image = new Image(stream);
            return Image;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
