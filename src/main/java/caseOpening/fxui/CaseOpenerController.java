package caseOpening.fxui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import caseOpening.openingCases.CaseRegular;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/*
 * Class that spins cases
 */
public class CaseOpenerController implements Initializable {
    private static CaseRegular goldenCase = new CaseRegular();
    @FXML private ImageView showCaseWeapon1, showCaseWeapon2, showCaseWeapon3, showCaseWeapon4;

    @FXML
    public void spinCase(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set 4 random weapons on loading of scene
        try{
            showCaseWeapon1.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon2.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon3.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon4.setImage(goldenCase.getPrizeWeapon().getImage());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
