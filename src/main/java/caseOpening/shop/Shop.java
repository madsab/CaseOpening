package caseOpening.shop;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import caseOpening.logIn.User;
import caseOpening.weapons.Weapons;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Shop {
    private List<Weapons> Top2Images = new ArrayList<>();
    private List<Weapons> Bottom4Images = new ArrayList<>();
    private ImageView[] imagesToReplace;
    private Pane ShopPane;
    private User activeUser;

    public Shop(List<Weapons> Top2Images, List<Weapons> Bottom4Images, User activeUser,Pane ShopPage,ImageView ... imagesToReplace){
        if(!(Top2Images.size() == 2) || !(Bottom4Images.size() == 4)){
            throw new IllegalArgumentException("Top2Images can only contain 2 images, and Bottom4images can only contain 4 images");
        }
        for (Weapons weapon : Top2Images){
            weapon.setValue(weapon.getValue() + (weapon.getValue()*4));
            this.Top2Images.add(weapon);
        }   
        for (Weapons weapon : Bottom4Images){
            weapon.setValue(weapon.getValue() + (weapon.getValue()*2));
            this.Bottom4Images.add(weapon);
        }
        this.imagesToReplace = imagesToReplace;
        this.ShopPane = ShopPage;
        this.activeUser = activeUser;
    }


    public void addToShop(){
        int counter = 0;
        for (ImageView image : this.imagesToReplace){
            try{
                ImageView keyImage = new ImageView(new Image(new FileInputStream("./images/keyBlue.png")));
                keyImage.setOnMouseClicked((MouseEvent event) -> {
                    buyWeapon(event ,activeUser);
                });
                Label value = new Label();
                value.setStyle("-fx-text-fill: white;");
                if (counter < 2){
                    keyImage.setFitHeight(30);
                    keyImage.setFitWidth(30);
                    keyImage.setLayoutX(image.getLayoutX() + 150);
                    keyImage.setLayoutY(image.getLayoutY());
                    image.setImage(Top2Images.get(counter).getImage());
                    image.setId(Top2Images.get(counter).getName());
                    value.setText(": " + (Top2Images.get(counter).getValue()));
                    value.setLayoutX(image.getLayoutX() + 180);
                    value.setLayoutY(image.getLayoutY());
                } else {
                    keyImage.setFitHeight(20);
                    keyImage.setFitWidth(20);
                    keyImage.setLayoutX(image.getLayoutX() + 80);
                    keyImage.setLayoutY(image.getLayoutY());
                    image.setImage(Bottom4Images.get(counter-2).getImage());
                    image.setId(Bottom4Images.get(counter-2).getName());
                    value.setText(": " + (Bottom4Images.get(counter-2).getValue()));
                    value.setLayoutX(image.getLayoutX() + 100);
                    value.setLayoutY(image.getLayoutY());
                }
                this.ShopPane.getChildren().addAll(keyImage, value);

            } catch (Exception e){
                e.printStackTrace();
            }
            counter++;
        }
    }

    public void buyWeapon(MouseEvent event, User user){
        String weaponName = event.getPickResult().getIntersectedNode().getId();
        for (Weapons weapon : Top2Images){
            if(weapon.getName().equals(weaponName)){
                user.addWeapon(weapon);
                user.removeKeys(weapon.getValue());
            }
        }
        for (Weapons weapon : Bottom4Images){
            if(weapon.getName().equals(weaponName)){
                user.addWeapon(weapon);
                user.removeKeys(weapon.getValue());
            }
        }
    }

    
}
