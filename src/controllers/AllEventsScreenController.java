package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AllEventsScreenController implements Initializable {

    @FXML
    private HBox windowHeader;

    @FXML
    private TextField eventSearchField;

    @FXML
    private Button eventsTypeButton;

    @FXML
    private Label name_label;

    @FXML
    private Label editInfo_button;

    @FXML
    private Label logout_button;

    @FXML
    private Label ongoingEvents_toggle;

    @FXML
    private Label finishedEvents_toggle;

    @FXML
    private Label userRole_label;

    @FXML
    private VBox mainCardHeader;

    @FXML
    private Label eventName_label;

    @FXML
    private Label eventDesc_label;

    @FXML
    private ComboBox<?> location_comboBox;

    @FXML
    private ComboBox<?> date_comboBox;

    @FXML
    private ComboBox<?> roles_comboBox;

    @FXML
    private Label role_description;

    @FXML
    private Button join_button;

    @FXML
    private Button concern_button;

    @FXML
    private Label volCount_label;

    @FXML
    void onEditInfo(MouseEvent event) {

    }

    @FXML
    void onFinishedToggle(MouseEvent event) {

    }

    @FXML
    void onJoinEvent(ActionEvent event) {

    }

    @FXML
    void onLogout(MouseEvent event) {

    }

    @FXML
    void onOngoingToggle(MouseEvent event) {

    }

    @FXML
    void onSubmitConcern(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
