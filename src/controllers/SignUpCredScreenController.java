package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpCredScreenController {

    @FXML
    private TextArea username_field;

    @FXML
    private TextArea password_field;

    @FXML
    private TextArea cpassword_field;

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
//            FXMLLoader loader = FXMLLoader.load(getClass().getClassLoader().getResource("../resources/view/SignUpInfoScreen.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/SignUpInfoScreen.fxml"));
            SignUpInfoScreen controller = new SignUpInfoScreen();

            // TODO: input validation if username is already taken
            controller.setUsername(username_field.getText().trim());

            if (password_field.getText().trim().equals(cpassword_field.getText().trim())) {
                controller.setPassword(password_field.getText());
            }else {
                // TODO: input validation response to user
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
