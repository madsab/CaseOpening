package caseOpening.fxui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import caseOpening.logIn.User;
import caseOpening.shop.Shop;
import caseOpening.tools.WeaponRarityComparator;
import caseOpening.weapons.Knife;
import caseOpening.weapons.Weapons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class homePageController implements Initializable{
    @FXML private Button ShopLayoutButton, caseButton, startAssignmentButton, ShopButton, YourWeaponsButton;
    @FXML private ImageView lobbyCharacter, caseIcon, ShopIcon, homePageBackground, keyImage, keyImage2, WeaponTopLeftShop, WeaponTopRightShop, WeaponBottom1Shop, WeaponBottom2Shop, WeaponBottom3Shop, WeaponBottom4Shop;
    @FXML private Label homePageInfo, amountKeysLabel,amountKeysLabel2, usernameShowLabel, ShopResponse;
    @FXML private Pane ShopPane, ShopLayoutPane;
    @FXML private ScrollPane YourWeaponsPane;
    private String CaseOpeningInfo = "Place your bets and push your luck in an exiting \n case opening. You can aquire different \n weapons in different rarities. From common \n pistols to the legendary knife. Best of luck";
    private User activeUser;
    private Shop shop;
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
        ShopResponse.setVisible(false);
        getUserWeapons();
    }
    @FXML
    private void buyWeapon(MouseEvent event){
        try{
            shop.buyWeapon(event, this.activeUser);
            amountKeysLabel2.setText(": " + activeUser.getKeys());
            ShopResponse.setVisible(true);
            ShopResponse.setStyle("-fx-text-fill: green;");
            ShopResponse.setText("Purchased");
        } catch (IllegalStateException e){
            ShopResponse.setVisible(true);
            ShopResponse.setStyle("-fx-text-fill: red;");
            ShopResponse.setText("Not enough keys");
        }
    }

    @FXML
    private void sellWeapon(ActionEvent event){
        String weaponName = ((Button)event.getSource()).getId();
        for (Weapons weapon : activeUser.getWeapons()){
            if(weaponName.equals(weapon.getName())){
                activeUser.addKeys(weapon.getValue());
                activeUser.removeWeapon(weapon);
                break;
            }
        }
        amountKeysLabel.setText(": " + this.activeUser.getKeys());
        amountKeysLabel2.setText(": " + this.activeUser.getKeys());
        getUserWeapons();
        
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
        AnchorPane YourWeaponsContent = new AnchorPane();
        YourWeaponsContent.setStyle("-fx-background-color: #2a353b;");
        YourWeaponsContent.setPrefHeight(YourWeaponsPane.getPrefHeight());
        YourWeaponsContent.setPrefWidth(YourWeaponsPane.getPrefWidth());
        if(!weapons.isEmpty()){
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
                    sellButton.setCursor(Cursor.HAND);
                    sellButton.setId(weapon.getName());
                    sellButton.setOnAction(event -> {
                        sellWeapon(event);
                    });

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
        } else {
            YourWeaponsPane.setContent(YourWeaponsContent);
        }
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.activeUser = new User("src/main/resources/caseOpening/UserOverview.txt");
        List<Weapons> top2Images = new ArrayList<>(Arrays.asList(
            new Knife("BlackHawk", "weapons-BlackHawk.jpg", "legendary"), 
            new Knife("BloodMoney", "weapons-BloodMoney.jpg", "legendary")));
        List<Weapons> bottom4Images = new ArrayList<>(Arrays.asList(
            new Weapons("SniperBlueFire", "weapons-SniperBlueFire.jpg", "epic"),
            new Weapons("PistolSilencedBlueBoy", "weapons-PistolSilencedBlueBoy.jpg", "rare"),
            new Weapons("AK-47Rebel", "weapons-AK47Rebel.jpg", "uncommon"),
            new Weapons("SniperMSRTiger", "weapons-SniperMSRTiger.jpg", "uncommon")
        ));
        this.shop = new Shop(top2Images, bottom4Images, activeUser,ShopPane, WeaponTopLeftShop, WeaponTopRightShop, WeaponBottom1Shop, WeaponBottom2Shop, WeaponBottom3Shop, WeaponBottom4Shop);
        this.shop.addToShop();
        //On load set these images
        try {
            homePageBackground.setImage(new Image(new FileInputStream("./images/csgo_nuke_background.jpg")));
            caseIcon.setImage(new Image(new FileInputStream("./images/case_icon.png")));
            ShopIcon.setImage(new Image(new FileInputStream("./images/ShopIcon.png")));
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
