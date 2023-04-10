package caseOpening.fxui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import caseOpening.logIn.User;
import caseOpening.tools.WeaponNameComparator;
import caseOpening.tools.WeaponRarityComparator;
import caseOpening.weapons.Weapons;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class homePageController implements Initializable{
    @FXML private Button ShopLayoutButton, caseButton, startAssignmentButton, ShopButton, YourWeaponsButton;
    @FXML private ImageView lobbyCharacter, caseIcon, shootingRangeIcon, homePageBackground, keyImage, keyImage2;
    @FXML private Label homePageInfo, amountKeysLabel,amountKeysLabel2, usernameShowLabel;
    @FXML private Pane ShopPane, ShopLayoutPane;
    @FXML private ScrollPane YourWeaponsPane;
    @FXML private AnchorPane YourWeaponsContent;
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

    @FXML
    public void showShopLayout(){
        ShopLayoutPane.setVisible(true);
        caseButton.setStyle("-fx-background-color: none;");
        ShopLayoutButton.setStyle("-fx-border-color: white; -fx-border-radius: 10; -fx-background-color: none;");
        
    }

    @FXML
    public void showCaseOpening(){
        ShopLayoutPane.setVisible(false);
        ShopLayoutButton.setStyle("-fx-background-color: none;");
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

    @FXML
    public void showShop(){
        ShopButton.setStyle("-fx-background-color: none; -fx-border-color: gray; -fx-border-style: solid solid none solid; -fx-border-radius: 5px;");
        ShopPane.setStyle("-fx-background-color: none; -fx-border-color: gray; -fx-border-radius: 5px;");
        YourWeaponsButton.setStyle("-fx-background-color: none;");
        ShopPane.setVisible(true);
        YourWeaponsPane.setVisible(false);
    }

    @FXML
    public void showYourWeapons(){
        YourWeaponsButton.setStyle("-fx-background-color: none; -fx-border-color: gray; -fx-border-style: solid solid none solid; -fx-border-radius: 5px;");
        YourWeaponsPane.setStyle("-fx-background-color: none; -fx-border-color: gray; -fx-border-radius: 5px;");
        ShopButton.setStyle("-fx-background-color: none;");
        ShopPane.setVisible(false);
        YourWeaponsPane.setVisible(true);
        getUserWeapons();
    }
    @FXML
    public void buyWeapon(){

    }
    @FXML
    public void sellWeapon(Weapons weaponToSell){

    }

    /**
     * gets the active users weapons and adds them to the "Your Weapons" pane
     * <p>
     * adds a value and a "sell" button
     */
    @FXML
    public void getUserWeapons(){
        double layoutX = 10;
        double layoutY = 10;
        List<Weapons> weapons = activeUser.getWeapons();
        weapons.sort(new WeaponRarityComparator());
        for(Weapons weapon : weapons){
            try{
                ImageView keyImage = new ImageView(new Image(new FileInputStream("./images/keyBlue.png")));
                keyImage.setFitWidth(25);
                keyImage.setFitHeight(25);
                Label keyAmountLabel = new Label();
                keyAmountLabel.setText(": " + weapon.getValue());
                keyAmountLabel.setStyle("-fx-text-fill: white;");
                
                ImageView image = new ImageView(weapon.getImage());
                image.setFitWidth(160);
                image.setFitHeight(100);
                Button sellButton = new Button();
                sellButton.setText("Sell");
                sellButton.setStyle("-fx-background-color: #49768f; -fx-text-fill: white;");

                //Layout on the AnchorPane
                AnchorPane.setTopAnchor(image, layoutY);
                AnchorPane.setLeftAnchor(image, layoutX);
                AnchorPane.setTopAnchor(sellButton, layoutY+ 105);
                AnchorPane.setLeftAnchor(sellButton, layoutX + 60);
                AnchorPane.setTopAnchor(keyImage, layoutY + 105);
                AnchorPane.setLeftAnchor(keyImage, layoutX + 100);
                AnchorPane.setTopAnchor(keyAmountLabel, layoutY + 110);
                AnchorPane.setLeftAnchor(keyAmountLabel, layoutX + 125);
                YourWeaponsContent.getChildren().addAll(image, sellButton, keyImage, keyAmountLabel);
                YourWeaponsPane.setContent(YourWeaponsContent);
                layoutX += 200;
                if(layoutX >= 600){
                    layoutY += 150;
                    layoutX = 10;
                    if(layoutY > YourWeaponsContent.getPrefHeight()-30){
                        YourWeaponsContent.setPrefHeight(layoutY + image.getFitHeight() + 50);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Couldn't find weapons");                
            }
        }
        
        
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
            keyImage2.setImage(new Image(new FileInputStream("./images/keyBlue.png")));

            amountKeysLabel.setText(": " + this.activeUser.getKeys());
            amountKeysLabel2.setText(": " + this.activeUser.getKeys());
            usernameShowLabel.setText(this.activeUser.getUsername());
            usernameShowLabel.setText(this.activeUser.getUsername());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
