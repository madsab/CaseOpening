package caseOpening.fxui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import caseOpening.openingCases.CaseRegular;
import caseOpening.openingCases.CaseSpinner;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/*
 * Class that spins cases
 */
public class CaseOpenerController implements Initializable {
    private static CaseRegular goldenCase = new CaseRegular();
    @FXML private ImageView showCaseWeapon1, showCaseWeapon2, showCaseWeapon3, showCaseWeapon4, showCaseWeapon5;

    @FXML
    public void spinCase(){
        CaseSpinner caseSpinner = new CaseSpinner(goldenCase,10,showCaseWeapon1,showCaseWeapon2,showCaseWeapon3,showCaseWeapon4, showCaseWeapon5);
        caseSpinner.spinCase();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set 5 random weapons on loading of scene
        try{
            showCaseWeapon1.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon2.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon3.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon4.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon5.setImage(goldenCase.getPrizeWeapon().getImage());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
