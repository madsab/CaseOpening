package caseOpening.fxui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import caseOpening.logIn.User;
import caseOpening.openingCases.CaseRegular;
import caseOpening.openingCases.CaseSpinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for spinning a case
 */
public class CaseOpenerController implements Initializable {
    private static CaseRegular goldenCase = new CaseRegular();
    @FXML private ImageView showCaseWeapon1, showCaseWeapon2, showCaseWeapon3, showCaseWeapon4, showCaseWeapon5, caseOpenedBackground, showWeaponWon, backIcon, keyImageView;
    @FXML private Pane ShowWeaponPane, noMoreKeysPane;
    @FXML private Label weaponName, keyAmount;
    private CaseSpinner caseSpinner;
    private User activeUser;

    @FXML
    public void spinCase(){
        if(this.activeUser.getKeys() == 0){
            noMoreKeysPane.setVisible(true);
        }
        if(!(caseSpinner.isSpinning()) && this.activeUser.getKeys() > 0){
            this.activeUser.removeKeys(1);
            keyAmount.setText(": " + this.activeUser.getKeys());
            caseSpinner.spinCase(10, 0.002, ShowWeaponPane, showWeaponWon, weaponName);
        }
    }

    @FXML
    public void hideShowWeapon(){
        ShowWeaponPane.setVisible(false);
    }

    @FXML
    public void hideNoMoreKeys(){
        noMoreKeysPane.setVisible(false);
    }

    @FXML
    public void toCaseOpenerHomePage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/caseOpenerMainPage.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.activeUser = new User("src/main/resources/caseOpening/UserOverview.txt");
        //Set 5 random weapons on loading of scene and user key info
        try{
            caseOpenedBackground.setImage(new Image(new FileInputStream("./images/weapons-case-opened.png")));
            backIcon.setImage(new Image(new FileInputStream("./images/backIcon.png")));
            showCaseWeapon1.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon2.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon3.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon4.setImage(goldenCase.getPrizeWeapon().getImage());
            showCaseWeapon5.setImage(goldenCase.getPrizeWeapon().getImage());
            keyImageView.setImage(new Image(new FileInputStream("./images/keyBlue.png")));
            keyAmount.setText(": " + this.activeUser.getKeys());
            caseSpinner = new CaseSpinner(this.activeUser.getUsername(), "src/main/resources/caseOpening/UserOverview.txt", goldenCase,showCaseWeapon1,showCaseWeapon2,showCaseWeapon3,showCaseWeapon4, showCaseWeapon5);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
