package caseOpening;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
      stage.setTitle("Counter Strike Global Retard Version");
      stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("logIn.fxml"))));
      stage.show();
    }

}
