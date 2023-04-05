package caseOpening.fxui;

import java.io.IOException;

import caseOpening.fileWriting.UserFileWriterReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    @FXML private TextField username, password;
    @FXML private Label logInResponse;
    
    //Takes you to homepage
    @FXML
    public void onLogIn(ActionEvent event) throws IOException{
        UserFileWriterReader fr = new UserFileWriterReader();
        if(fr.getUser(username.getText(), "src/main/resources/caseOpening/UserOverview.txt") == null){
            logInResponse.setText("Can't find user");
        } else if (!password.getText().equals(fr.getFromUser("password", username.getText(), "src/main/resources/caseOpening/UserOverview.txt"))){
            logInResponse.setText("Not correct password");
        }
        else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/homePage.fxml"));
            Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //Takes you to create account page
    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("caseOpening/createAccount.fxml"));
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
}
