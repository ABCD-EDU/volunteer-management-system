package controllers;

import client.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInScreenController {

    @FXML
    private Label username_field;

    @FXML
    private Label password_field;

    @FXML
    private Button login_button;

    @FXML
    private Label signup_button;

    @FXML
    void onLogin(ActionEvent event) {

    }

    @FXML
    void onSignUpPress(MouseEvent event) {
        System.out.println("Register button pressed");
        try {
            Stage window = (Stage)signup_button.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass()
                    .getResource("../resources/view/SignUpCredScreen.fxml"))));
            window.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
