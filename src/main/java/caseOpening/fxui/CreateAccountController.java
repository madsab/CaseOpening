package caseOpening.fxui;

import java.io.IOException;

import caseOpening.fileWriting.UserFileWriterReader;
import caseOpening.logIn.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
    @FXML private TextField createdUsername, createdPassword;
    @FXML private Label createdUserResponse;
    @FXML private UserFileWriterReader fr = new UserFileWriterReader();

    @FXML
    public void createNewAccount(ActionEvent event) throws IOException{
        try {
            User loginUser = new User(createdUsername.getText(), createdPassword.getText());
            loginUser.addUserToFile("src/main/resources/caseOpening/UserOverview.txt");
            createdUserResponse.setText("User created");
            createdUserResponse.setStyle("-fx-text-fill: green;");
            fr.overrideFile(loginUser.getUsername(), "src/main/resources/caseOpening/ActiveUser.txt");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/homePage.fxml"));
            Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            if(e.getClass().equals(IllegalArgumentException.class)){
                createdUserResponse.setText("Username can't have special characters. \nPassword must have one number and one capital letter");
                createdUserResponse.setStyle("-fx-text-fill: red;");
            }
            if(e.getClass().equals(IllegalStateException.class)){
                createdUserResponse.setText("User allready exists");
                createdUserResponse.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    public void toLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/logIn.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
