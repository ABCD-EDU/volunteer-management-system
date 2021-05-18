package controllers;

import client.DBConnector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import models.Concern;
import models.Event;
import models.EventSchedule;
import models.Volunteer;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SubmitConcernScreenController implements Initializable{

    private Volunteer vol;
    private EventSchedule es;

    @FXML
    Button cancel_button;

    @FXML
    Button submit_button;

    @FXML
    Pane upper_pane;

    @FXML
    VBox middle_vbox;

    @FXML
    HBox bottom_hbox;

    String currentlyChosenEvent;

    public void setVol(Volunteer vol) {
        this.vol = vol;
    }

    public void setEs(EventSchedule es) {
        this.es = es;
    }

    public void setCurrentlyChosenEvent(String eventName){
        currentlyChosenEvent = eventName;
        //The "<Event Name> Concern" label
        ((Label)upper_pane.getChildren().get(0)).setText(eventName+" Concern");
    }


    @FXML
    public void onCancel(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) cancel_button.getScene().getWindow();
        stage.close();
    }

    public void onSubmit(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.NONE, "Confirm concern submission?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            String concernType = "";
            String description = "";
            for (Node component: middle_vbox.getChildren()){
                if (component instanceof ComboBox){
                    concernType = ((ComboBox<String>) component).getSelectionModel().getSelectedItem();
                }else if (component instanceof TextArea){
                    description = ((TextArea) component).getText();
                }
            }
            //Call insertconcern method
            DBConnector.insertConcern(new Concern(concernType, description, vol.getUserId(), es.getSchedId())); //userId and event Id are temporary. Need a way to have access to them.
            Stage stage = (Stage) cancel_button.getScene().getWindow();
            stage.close();
        }
    }
    
    public void initializeComboBox(){
        List<Concern.Type> yourEnums = Arrays.asList(Concern.Type.values());
        List<String> enumsInString = yourEnums.stream()
                .map(Enum::toString)
                .collect(Collectors.toList());

        for (Node component: middle_vbox.getChildren()){
            if (component instanceof ComboBox){
                ((ComboBox<String>)component).getItems().addAll(enumsInString);
                ((ComboBox<String>)component).setPromptText("SELECT CONCERN TYPE...");
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBox();
    }
}
