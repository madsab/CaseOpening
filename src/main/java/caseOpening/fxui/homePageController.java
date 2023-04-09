package caseOpening.fxui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import caseOpening.logIn.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class homePageController implements Initializable{
    @FXML private Button shootingRangeButton, caseButton;
    @FXML private ImageView lobbyCharacter, caseIcon, shootingRangeIcon, homePageBackground, keyImage;
    @FXML private Label homePageInfo, amountKeysLabel, usernameShowLabel;
    @FXML private Button startAssignmentButton;
    private String ShootingRangeInfo = "Test your weapons at the shooting range. \n Nothing better than firing a couple of bullets \n at your enemies, and of course feeling the \n rush of shooting what you own. Be safe";
    private String CaseOpeningInfo = "Place your bets and push your luck in an exiting \n case opening. You can aquire different \n weapons in different rarities. From common \n pistols to the legendary knife. Best of luck";
    private User activeUser;
    //Takes user to CaseOpening main page
    @FXML
    public void toCaseOpening(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/caseOpenerMainPage.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Takes user to ShootingRange
    @FXML
    public void toShootingRange(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/shootingRange.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showShootingRange(){
        caseButton.setStyle("-fx-background-color: none;");
        shootingRangeButton.setStyle("-fx-border-color: white; -fx-background-color: none; -fx-border-radius: 10;");
        startAssignmentButton.setText("Start shootin");
        amountKeysLabel.setVisible(false);
        keyImage.setVisible(false);
        startAssignmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                try {
                    toShootingRange(e);
                } catch (IOException e1) {
                    System.out.println("Feil");
                }
            }
        });
        homePageInfo.setText(ShootingRangeInfo);

        
    }

    @FXML
    public void showCaseOpening(){
        shootingRangeButton.setStyle("-fx-background-color: none;");
        caseButton.setStyle("-fx-border-color: white; -fx-border-radius: 10; -fx-background-color: none;");
        startAssignmentButton.setText("Open Cases");
        amountKeysLabel.setVisible(true);
        keyImage.setVisible(true);
        startAssignmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                try {
                    toCaseOpening(e);
                } catch (IOException e1) {
                    System.out.println("kom hit");
                }
            }
        });
        homePageInfo.setText(CaseOpeningInfo);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.activeUser = new User("src/main/resources/caseOpening/UserOverview.txt");
        //On load set these images
        try {
            homePageBackground.setImage(new Image(new FileInputStream("./images/csgo_nuke_background.jpg")));
            caseIcon.setImage(new Image(new FileInputStream("./images/case_icon.png")));
            shootingRangeIcon.setImage(new Image(new FileInputStream("./images/shootingRange_icon.png")));
            lobbyCharacter.setImage(new Image(new FileInputStream("./images/soldier_standAnimation_lobby.gif")));
            keyImage.setImage(new Image(new FileInputStream("./images/keyBlue.png")));

            amountKeysLabel.setText(": " + this.activeUser.getKeys());
            usernameShowLabel.setText(this.activeUser.getUsername());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
