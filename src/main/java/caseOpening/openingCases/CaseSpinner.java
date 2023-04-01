package caseOpening.openingCases;



import caseOpening.weapons.Weapons;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CaseSpinner {
    private ImageView[] imageList;
    private Case activeCase;
    private int recursiveStopper = 0;
    
    public CaseSpinner(Case activCase, ImageView ... weaponImages){
        this.imageList = weaponImages;
        this.activeCase = activCase;
    }
        
    //1060 at end
    //68 at start
    /**
     *  Spins the case for {@code duration} seconds and stops to give User a prize weapon}
     */
    public void spinCase(double duration, double speed){
        // IN PROGRESS
        // for (ImageView image : imageList){
        //     TranslateTransition transition = new TranslateTransition(new Duration(2000));
        //     transition.setNode(image);
        //     transition.setByX(190);
        //     System.out.println(transition.getNode().getLayoutX());
        //     if(image.getLayoutX() >= 1058){
        //         try{
        //             image.setImage(activeCase.getPrizeWeapon().getImage());
        //         } catch (Exception exeption){
        //             exeption.printStackTrace();
        //         }
        //         image.setLayoutX(68);
        //         transition.setToX(0);
        //     }
        //     transition.play();
        // }
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
        System.out.println(speed);
        timeline.setOnFinished(e -> {
            if(recursiveStopper < 30){
                recursiveStopper++;
                if(recursiveStopper >= 20){
                    spinCase(duration- (duration/20), (speed*1.3));    
                }else {
                    spinCase(duration - (duration/10), (speed*1.1));
                }
            } else {
                timeline.stop();
            }
            //0.001 0.005 0.01 0.5
        });    
    }

    /**
     * Picks a random {@code Weapon} for the User to win
     * <p>
     * Can be anything in the active Case's library
     */
    public Weapons getUserPrize(){
        Weapons weaponWon = activeCase.getPrizeWeapon();
        System.out.println(weaponWon.getName());
        return weaponWon;
    }
}
