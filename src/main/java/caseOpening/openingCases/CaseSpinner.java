package caseOpening.openingCases;



import java.io.IOException;

import caseOpening.logIn.User;
import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapons;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CaseSpinner {
    private ImageView[] imageList;
    private Case activeCase;
    private int recursiveStopper = 0;
    private boolean hasChosenWeapon, isSpinning;
    private Weapons currentWonWeapon;
    private User activeUser;
    
    public CaseSpinner(String activeUsername, String filePath, Case activCase, ImageView ... weaponImages){
        this.imageList = weaponImages;
        this.activeCase = activCase;
        this.activeUser = new User(activeUsername, filePath, true);
        
    }
        
    //1060 at end
    //68 at start
    /**
     *  Spins the case for {@code duration} seconds at {@code speed} per frame. stops to give User a prize weapon}
     * <p>
     * Also takes in parameters {@code Pane}, {@code ImageView}, {@code Label} for showing User the weapon on screen
     */
    public void spinCase(double duration, double speed, Pane pane, ImageView imageView, Label label){
        isSpinning = true;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(speed), e -> {
            for (ImageView image : imageList){
                image.setLayoutX(image.getLayoutX() + 2);
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
        timeline.setCycleCount((40*(int)duration));
        timeline.play(); 
        timeline.setOnFinished(e -> {
            if(recursiveStopper < 30){
                recursiveStopper++;
                //When coming to and end set WonWeapon and slow speed
                if(recursiveStopper >= 19){
                    for (ImageView image : imageList){
                        if(image.getLayoutX() > 68 && image.getLayoutX() < 268 && !hasChosenWeapon){
                            getUserPrize();
                            try {
                                if(currentWonWeapon.getRarity().equals("legendary")){
                                    image.setImage(((Knife)currentWonWeapon).getHidingImage());
                                } else {
                                    image.setImage(currentWonWeapon.getImage());
                                }
                            } catch (IOException e1) {
                                System.out.println("Holder meg til ett våpen");
                            }
                        }
                    }
                }
                if(recursiveStopper >= 20){
                    spinCase(duration- (duration/20), (speed*1.3), pane, imageView, label);    
                }else {
                    spinCase(duration - (duration/10), (speed*1.1), pane, imageView, label);
                }
            } else {
                timeline.stop();
                label.setText(currentWonWeapon.getName());
                try {
                    imageView.setImage(currentWonWeapon.getImage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                pane.setVisible(true);
                //Reset counters and booleans
                isSpinning = false;
                recursiveStopper = 0;
                hasChosenWeapon = false;

                //Add weapons to user
                activeUser.addWeapon(currentWonWeapon);
            }
        });
        
    }

    /**
     * Picks a random {@code Weapon} for the User to win
     * <p>
     * Can be anything in the active Case's library but will only choose ONE weapon,
     * <p>
     * even when run more than ones in the same code
     */
    public Weapons getUserPrize(){
        if(!hasChosenWeapon){
            this.hasChosenWeapon = true;
            Weapons weaponWon = activeCase.getPrizeWeapon();
            if(weaponWon.getRarity().equals("legendary")){
                weaponWon = activeCase.pickRandomKnife();
            }
            this.currentWonWeapon = weaponWon;
            return weaponWon;
        }
        return null;
    }
    
    public boolean isSpinning(){
        return this.isSpinning;
    }
}
