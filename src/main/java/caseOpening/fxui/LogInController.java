package caseOpening.fxui;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class LogInController {
    @FXML private TextField username, password;
    
    @FXML
    public void onLogIn(){
        System.out.println("Lol");
    }
}
