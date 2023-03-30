package caseOpening.fxui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class CaseOpenerHomeController implements Initializable{
    @FXML private ImageView case_black, case_snake, case_spin, backIcon;

    @FXML
    public void OpenCase(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/caseOpener.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toMainPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/homePage.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            case_black.setImage(new Image(new FileInputStream("./images/weapons-case-black.png")));
            case_snake.setImage(new Image(new FileInputStream("./images/weapons-case-snake.png")));
            case_spin.setImage(new Image(new FileInputStream("./images/weapons-case-spin.png")));
            backIcon.setImage(new Image(new FileInputStream("./images/backIcon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
