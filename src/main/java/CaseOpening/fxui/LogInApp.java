package CaseOpening.fxui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class LogInApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CS:MO");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("loggInPage.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        LogInApp.launch(LogInApp.class, args);
    }
     
}
