package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Concern;

import java.sql.Timestamp;

public class ClientExecutable extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/SignInScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Textra");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            DBConnector.setConnection();

            launch(args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
