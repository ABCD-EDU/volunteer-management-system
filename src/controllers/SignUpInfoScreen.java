package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpInfoScreen {

    private String username;
    private String password;

    @FXML
    private TextField fName_field;

    @FXML
    private TextField lName_field;

    @FXML
    private TextField sex_field;

    @FXML
    private TextField bday_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField dprogram_field;

    @FXML
    private TextField year_field;

    @FXML
    private TextField address_field;

    @FXML
    private Button create_button;

    @FXML
    private Label goback_label;

    @FXML
    void onCreateAccount(ActionEvent event) {
        // TODO: Implement
        System.out.println(username);
        System.out.println(password);
    }

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

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }

}
