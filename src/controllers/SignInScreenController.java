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
import models.Volunteer;

import java.io.IOException;
import java.sql.PreparedStatement;


public class SignInScreenController {

    @FXML
    private Label warning_label;

    @FXML
    private TextField username_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button login_button;

    @FXML
    private Label signup_button;

    /**
     * Algorithm:
     * 1. Check if all fields are not empty
     * 2. Check if username exist
     * 3. Check if password is correct
     */
    @FXML
    void onLogin(ActionEvent event) {
        String username = username_field.getText().trim();
        String password = password_field.getText().trim();

        // check if fields are empty
        if (username.isBlank() || password.isBlank()) {
            if (username.isBlank())
                warning_label.setText("Username field is blank");
            else
                warning_label.setText("password field is blank");
            warning_label.setVisible(true);
            return;
        }else {
            warning_label.setText("");
            warning_label.setVisible(false);
        }

        int response = DBConnector.login(username, password);
        if (response == 1) {
            System.out.println("Successful user log in");
            initializeLogin(username);
            return;
        }
        if (response == 2) // implement administrator log in here
            System.out.println("Administrator login");
        if (response == -2)
            warning_label.setText("There was an error while logging in");
        if (response == -1)
            warning_label.setText("Username does not exist");
        if (response == 0)
            warning_label.setText("Username and password does not match");
        warning_label.setVisible(true);
    }

    private void initializeLogin(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/AllEvents.fxml"));
            AllEventsScreenController controller = new AllEventsScreenController();

            controller.setVol(DBConnector.getVolunteer(username));

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage)login_button.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
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
