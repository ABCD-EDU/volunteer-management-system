package controllers;

import client.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpCredScreenController {

    @FXML
    private Label warning_label;

    @FXML
    private TextField username_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField cpassword_field;

    @FXML
    private Button next_button;

    @FXML
    private Label goback_label;

    @FXML
    void onGoBack(MouseEvent event) {
        try {
            Stage window = (Stage)goback_label.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass()
                    .getResource("../resources/view/SignInScreen.fxml"))));
            window.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onNext(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/SignUpInfoScreen.fxml"));
            SignUpInfoScreen controller = new SignUpInfoScreen();

            String username = username_field.getText().trim();
            String password = password_field.getText().trim();
            String cPassword = cpassword_field.getText().trim();

            if (username.isBlank() || password.isBlank() || cPassword.isBlank()) {
                warning_label.setText("Please fill in all input fields.");
                warning_label.setVisible(true);
                return;
            }

            if (DBConnector.usernameIsAvailable(username))
                controller.setUsername(username);
            else {
                warning_label.setText("username is already taken");
                warning_label.setVisible(true);
                return;
            }

            if (password.equals(cPassword)) {
                controller.setPassword(password);
            }else {
                warning_label.setText("Passwords do not match");
                warning_label.setVisible(true);
                return;
            }

            loader.setController(controller);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage)next_button.getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
