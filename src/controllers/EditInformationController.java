package controllers;

import client.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Volunteer;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditInformationController implements Initializable {

    private Volunteer vol;

    @FXML
    private Label warning_label;

    @FXML
    private TextField fName_field;

    @FXML
    private TextField lName_field;

    @FXML
    private TextField sex_field;

    @FXML
    private DatePicker date_picker;

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
    private Label changePass_button;

    @FXML
    private Button save_button;

    @FXML
    private Label cancel_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_field.setText(vol.getFirstName());
    }
}
