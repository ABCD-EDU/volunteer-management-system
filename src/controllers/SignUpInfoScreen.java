package controllers;

import client.DBConnector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.User;
import models.Volunteer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SignUpInfoScreen implements Initializable {

    private String username;
    private String password;

    @FXML
    private Label warning_label;

    @FXML
    private TextField fName_field;

    @FXML
    private TextField lName_field;

    @FXML
    private ComboBox<Volunteer.Sex> sex_field;

    @FXML
    private DatePicker date_picker;

    @FXML
    private TextField email_field;

    @FXML
    private TextField phone_field;

    @FXML
    private ComboBox<Volunteer.Type> type_field;

    @FXML
    private ComboBox<String> program_field;

    @FXML
    private ComboBox<Integer> year_field;

    @FXML
    private TextField address_field;

    @FXML
    private Button create_button;

    @FXML
    private Label goback_label;

    @FXML
    void onCreateAccount(ActionEvent event) {
        System.out.println("onCreateAccount");
        if (fName_field.getText().trim().isBlank() || lName_field.getText().trim().isBlank()
                || phone_field.getText().trim().isBlank() || address_field.getText().isBlank()
                || sex_field.getValue() == null || type_field.getValue() == null) {
            warning_label.setText("Please fill in all input fields.");
            warning_label.setVisible(true);
            return;
        }

        try {
            String number = phone_field.getText().trim();
            Long.parseLong(number);
            if (phone_field.getText().trim().length() != 11) {
                warning_label.setText("Inputted phone number is malformed");
                warning_label.setVisible(true);
                return;
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
            warning_label.setText("Inputted phone number is malformed");
            warning_label.setVisible(true);
            return;
        }

        if (type_field.getValue().equals(Volunteer.Type.STUDENT)) {
            if (program_field.getValue() == null) {
                warning_label.setText("Please indicate your degree program.");
                warning_label.setVisible(true);
                return;
            }
            if (year_field.getValue() == null) {
                warning_label.setText("Please indicate your collegiate year.");
                warning_label.setVisible(true);
                return;
            }
        }

        Volunteer volunteer = null;

        if (type_field.getValue().equals(Volunteer.Type.STUDENT))
            volunteer = new Volunteer(
                username, password, User.Type.VOLUNTEER, fName_field.getText(), lName_field.getText(),
                Date.valueOf(date_picker.getValue().toString()),address_field.getText(),
                Long.parseLong(phone_field.getText()), type_field.getValue(), year_field.getValue(),
                program_field.getValue(), sex_field.getValue());
        else
            volunteer = new Volunteer(
                    username, password, User.Type.VOLUNTEER, fName_field.getText(), lName_field.getText(),
                    Date.valueOf(date_picker.getValue().toString()),address_field.getText(),
                    Long.parseLong(phone_field.getText()), type_field.getValue(), -1,
                    null, sex_field.getValue());

        if (DBConnector.register(volunteer)) {
            showSuccessfulRegistration(username);
        }else {
            warning_label.setText("Internal error while trying to sign up user.");
            warning_label.setVisible(true);
        }
    }

    private void showSuccessfulRegistration(String username) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/view/SuccessfulRegistrationScreen.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Successful registration");

            for (Node component : ((Pane) root).getChildren()) {
                if (component.getId() != null) {
                    System.out.println(component.getId());
                    if (component.getId().equals("username_label"))
                        ((Label) component).setText(username);
                    if (component.getId().equals("okay_button")) {
                        component.setOnMouseClicked(e -> {
                            stage.close();
                            swapToLoginScreen();
                        });
                    }
                }
            }
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onGoBack(MouseEvent event) {
        swapToLoginScreen();
    }

    private void swapToLoginScreen() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Integer> years = new ArrayList<>();
        for (int i = 1; i < 5; i++)
            years.add(i);

        List<Volunteer.Type> types =  new ArrayList<>();
        types.add(Volunteer.Type.STUDENT);
        types.add(Volunteer.Type.FACULTY);

        List<Volunteer.Sex> sex = new ArrayList<>();
        sex.add(Volunteer.Sex.FEMALE);
        sex.add(Volunteer.Sex.MALE);

        List<String> degreePrograms = new ArrayList<>();
        degreePrograms.add("BS Computer Science");
        degreePrograms.add("BS Information Technology");
        degreePrograms.add("BS Mechanical Engineering");
        degreePrograms.add("BS Veterenary");
        degreePrograms.add("BS Nursing");

        year_field.setItems(FXCollections.observableList(years));
        type_field.setItems(FXCollections.observableList(types));
        sex_field.setItems(FXCollections.observableList(sex));
        program_field.setItems(FXCollections.observableList(degreePrograms));
    }

}
