package controllers;

import client.DBConnector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Event;
import models.Volunteer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AllEventsScreenController implements Initializable {

    private Volunteer vol;
    private SubmitConcernScreenController submitConcernScreenController;

    public VBox vboxVol1;
    public VBox vboxVol2;
    public VBox vboxVol3;
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
    private VBox events_vbox;

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

    public void setVol(Volunteer vol) {
        this.vol = vol;
    }

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
        // TODO: Are you sure panel
        System.exit(0);
    }

    @FXML
    void onOngoingToggle(MouseEvent event) {

    }

    @FXML
    void onSubmitConcern(ActionEvent event) {
        try {
            showSubmitConcernScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void showSubmitConcernScreen() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../resources/view/SubmitConcernScreen.fxml"));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(loader.load()));
            submitConcernScreenController = loader.getController();
            submitConcernScreenController.setCurrentlyChosenEvent(eventName_label.getText());

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadVolunteers(int schedId) {
        List<String> volunteers = DBConnector.getVolunteers(schedId);
        int division = Math.round(((float)volunteers.size()/3));
        System.out.println(division + " " + volunteers.size());

        for (int i = 0; i != division ; i++) {
            Label vol = new Label(volunteers.get(i));
            vboxVol1.getChildren().add(vol);
        }

        for (int i = division; i < division*2; i++) {
            Label vol = new Label(volunteers.get(i));
            vboxVol2.getChildren().add(vol);
        }

        for (int i = division*2; i != volunteers.size(); i++) {
            Label vol = new Label(volunteers.get(i));
            vboxVol3.getChildren().add(vol);
        }

        int volLimit = DBConnector.getVolunteerLimit(schedId);
        volCount_label.setText(volunteers.size() + "/" + volLimit);
    }

    private void initializeOngoingAllEventsScene() {
        System.out.println(DBConnector.getAllOngoingEvents(vol.getVolId()));
        ArrayList<Event> events = DBConnector.getAllOngoingEvents(vol.getVolId());
        assert events != null;
        try {
            Platform.runLater(() -> events_vbox.getChildren().clear());
            for (Event e : events) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/EventCard.fxml"));
                Node node = loader.load();
                String date = "date";

                // set onMouseClick
                node.setOnMouseClicked((event) -> {
                    eventName_label.setText(e.getName());
                    eventDesc_label.setText(e.getDescription());
                });

                // set card properties
                for (Node component : ((VBox)(((Pane) node).getChildren()).get(0)).getChildren()) {
                    if (component.getId().equals("name_label"))
                        ((Label) component).setText(e.getName());
                    if (component.getId().equals("date_label"))
                        ((Label) component).setText(date);
                }

                Platform.runLater(() -> events_vbox.getChildren().add(node));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeOngoingAllEventsScene();
        name_label.setText(vol.getFirstName() + " " + vol.getLastName());
    }

}
