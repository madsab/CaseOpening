package caseOpening.openingCases;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class CaseSpinner {
    private ImageView[] imageList;
    private int duration;
    private Case activeCase;
    
    public CaseSpinner(Case activCase, int duration, ImageView ... weaponImages){
        if(duration <= 0){
            throw new IllegalArgumentException("Inavlid value");
        }
        this.duration = duration;
        this.imageList = weaponImages;
        this.activeCase = activCase;
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
                for (ImageView image : imageList){
                    image.setLayoutX(image.getLayoutX() + 1);
                    if(image.getLayoutX() > 1060){
                        try{
                            image.setImage(activeCase.getPrizeWeapon().getImage());
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        image.setLayoutX(68);
                    }
                }
            }
        };
        
    //1060 at end
    //68 at start
    /*
     *  Spins the case
     */
    public void spinCase(){
        timer.start();
        try {
            TimeUnit.SECONDS.sleep(this.duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        
    }
}
