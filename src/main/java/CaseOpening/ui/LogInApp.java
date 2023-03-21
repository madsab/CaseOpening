package CaseOpening.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
public class LogInApp extends Application{

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("My App");
        primaryStage.setScene(FXMLLoader.load(LogInApp.class.getResource("loggInPage.fxml")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        LogInApp.launch(args);
    }
     
}
