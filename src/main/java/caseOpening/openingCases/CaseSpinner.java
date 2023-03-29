package caseOpening.openingCases;


import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CaseSpinner {
    private ImageView[] imageList;
    private Case activeCase;
    
    public CaseSpinner(Case activCase, ImageView ... weaponImages){
        this.imageList = weaponImages;
        this.activeCase = activCase;
    }
        
    //1060 at end
    //68 at start
    /*
     *  Spins the case
     */
    public void spinCase(int duration){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.001), e -> {
            for (ImageView image : imageList){
                image.setLayoutX(image.getLayoutX() + 1);
                if(image.getLayoutX() > 1060){
                    try{
                        image.setImage(activeCase.getPrizeWeapon().getImage());
                    } catch (Exception exeption){
                        exeption.printStackTrace();
                    }
                    image.setLayoutX(68);
                }
            }
        }));
        timeline.setCycleCount(500*duration);
        timeline.play();    
    }
}
