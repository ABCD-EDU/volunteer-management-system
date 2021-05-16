package controllers;

import client.DBConnector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
        Volunteer volunteer = new Volunteer(
                username, password, User.Type.VOLUNTEER, fName_field.getText(), lName_field.getText(),
                Date.valueOf(date_picker.getValue().toString()),"address hatdog",
                Integer.parseInt(phone_field.getText()), Volunteer.Type.STUDENT, year_field.getValue(),
                program_field.getValue(), Volunteer.Sex.FEMALE);
        DBConnector.register(volunteer);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Integer> years = new ArrayList<>();
        for (int i = 1; i < 11; i++)
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
