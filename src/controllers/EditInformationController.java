package controllers;

import client.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Volunteer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditInformationController implements Initializable {

    private Volunteer vol;
    private Stage parentStage;

    public void setVol(Volunteer vol) {
        this.vol = vol;
    }

    EditInformationController (Volunteer vol, Stage parentStage){
        this.vol = vol;
        this.parentStage = parentStage;
    }

    @FXML
    private Label warning_label;

    @FXML
    private TextField fName_field;

    @FXML
    private TextField lName_field;

    @FXML
    private ComboBox<String> sex_field;

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
    private Button save_button;


    @FXML
    private Button changepassword_button;




    @FXML
    private void onSaveChanges(){

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

        if (address_field.getText().isBlank()) {
            warning_label.setText("Address field cannot be blank");
            warning_label.setVisible(true);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.NONE, "Confirm changes?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            DBConnector.updateAddressAndPhoneNumber(address_field.getText(), Long.parseLong(phone_field.getText()), vol.getVolId());
            initializeLogin(vol.getUsername());
        }
    }

    @FXML
    private void onCancel(){
        ((Stage)address_field.getScene().getWindow()).close();
        initializeLogin(vol.getUsername());

    }

    //Change Password Components
    @FXML
    PasswordField currPassword_field;

    @FXML
    PasswordField newPass_field;

    @FXML
    PasswordField cNewPass_field;


    @FXML
    Button changepassword_save_button;

    @FXML
    Button changepass_cancel_button;

    @FXML
    private void onChangePassSave(){
        String password = DBConnector.getPassword(vol.getUserId());

        if (password==null || !password.equals(currPassword_field.getText())){
            Alert alert = new Alert(Alert.AlertType.NONE, "WRONG PASSWORD",ButtonType.OK);
            alert.showAndWait();
        }else if(password.equals(currPassword_field.getText())) {
            if (newPass_field.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "NEW PASSWORD FIELD BLANK", ButtonType.OK);
                alert.showAndWait();
             }else if (newPass_field.getText().equals(cNewPass_field.getText())) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Confirm changes?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    DBConnector.updatePassword(vol.getUserId(), newPass_field.getText());
                    ((Stage) changepass_cancel_button.getScene().getWindow()).close();
                }
            } else if (currPassword_field.getText().equals(newPass_field.getText())) {
                Alert alert = new Alert(Alert.AlertType.NONE, "PASSWORD UNCHANGE", ButtonType.OK);
                alert.showAndWait();
            } else if (!newPass_field.getText().equals(cNewPass_field.getText())) {
                Alert alert = new Alert(Alert.AlertType.NONE, "PASSWORD DOES NOT MATCH", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onChangePassCancel(){
        ((Stage)changepass_cancel_button.getScene().getWindow()).close();
    }

    //End change password components



    private void initializeLogin(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/EventsScreen.fxml"));
            AllEventsScreenController controller = new AllEventsScreenController();

            controller.setVol(DBConnector.getVolunteer(username));

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage)save_button.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fName_field.setText(vol.getFirstName());
        lName_field.setText(vol.getLastName());
        sex_field.setPromptText(vol.getSex().toString());
        phone_field.setText(vol.getPhone_number().toString());
        address_field.setText(vol.getAddress());
        program_field.setPromptText(vol.getDegreeProgram());
        year_field.setPromptText(Integer.toString(vol.getYear()));
        type_field.setPromptText(vol.getVolType().getType());
        date_picker.setValue(vol.getBirthDate().toLocalDate());
    }
    @FXML
    public void onChangePass(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../resources/view/ChangePasswordScreen.fxml"));
            loader.setController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
