package controllers;

import client.DBConnector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Event;
import models.EventSchedule;
import models.Role;
import models.Volunteer;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

public class AllEventsScreenController implements Initializable {

    private EventSchedule selectedSched;
    private Role selectedRole;
    private Volunteer vol;
    private SubmitConcernScreenController submitConcernScreenController;

    private Event selectedEvent;

    /**
     * Values: ALL_EVENTS, MY_EVENTS
     */
    private String eventsType = "ALL_EVENTS";

    /**
     * Values: ONGOING, FINISHED
     */
    private String eventsSortType = "ONGOING";

    public VBox vboxVol1;
    public VBox vboxVol2;
    public VBox vboxVol3;
    @FXML
    private HBox windowHeader;

    @FXML
    private TextField eventSearch_field;

    @FXML
    private Button eventsType_button;

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
    private ComboBox<EventSchedule> sched_comboBox;

    @FXML
    private ComboBox<Role> roles_comboBox;

    @FXML
    private Label rolesDesc_label;

    @FXML
    private Button join_button;

    @FXML
    private Button concern_button;

    @FXML
    private Label volCount_label;

    @FXML
    private Label programReq_label;

    @FXML
    private Label yearReq_label;

    @FXML
    private Label location_label;

    @FXML
    private Label startDateTime_label;

    @FXML
    private Label endDateTime_label;

    @FXML
    private Pane rightCard;

    @FXML
    private Pane rightHeader;

    @FXML
    private ScrollPane rightPane;

    public void setVol(Volunteer vol) {
        this.vol = vol;
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
            submitConcernScreenController.setVol(vol);
            submitConcernScreenController.setEs(selectedSched);

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void onEditInfo(MouseEvent event) {
       try {
           showEditInfoScreen();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void showEditInfoScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../resources/view/EditInformation.fxml"));

            EditInformationController editInformationController = new EditInformationController(vol,
                    (Stage) concern_button.getScene().getWindow());
            loader.setController(editInformationController);

            Stage stage = (Stage) concern_button.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void onEventsTypeToggle(ActionEvent event) {
        if (eventsType.equals("ALL_EVENTS")) {
            eventsType = "MY_EVENTS";
            windowHeader.setStyle("-fx-background-color:  #ffcb77");
            editInfo_button.setStyle("-fx-text-fill: #444444");
            name_label.setStyle("-fx-text-fill: #444444");
            logout_button.setStyle("-fx-text-fill: #fe6d73");
            eventsType_button.setStyle("-fx-background-color:  #227C9D; -fx-text-fill: #ffffff");

        }else {
            eventsType = "ALL_EVENTS";
            windowHeader.setStyle("-fx-background-color:  #227C9D");
            editInfo_button.setStyle("-fx-text-fill: #ffffff");
            name_label.setStyle("-fx-text-fill: #ffffff");
            logout_button.setStyle("-fx-text-fill: #FFCB77");
            eventsType_button.setStyle("-fx-background-color:  #FFCB77; -fx-text-fill: #444444");
        }

        if (eventsType_button.getText().equals("All Events"))
            eventsType_button.setText("My Events");
        else
            eventsType_button.setText("All Events");

        updateEventsPanel();
    }

    private void updateEventsPanel() {
        if (eventsSortType.equals("ONGOING") && eventsType.equals("ALL_EVENTS"))
            initializeEventsPanel(Objects.requireNonNull(DBConnector.getAllOngoingEvents()));
        if (eventsSortType.equals("ONGOING") && eventsType.equals("MY_EVENTS"))
            initializeEventsPanel(Objects.requireNonNull(DBConnector.getOngoingJoinedEvents(vol.getVolId())));
        if (eventsSortType.equals("FINISHED") && eventsType.equals("ALL_EVENTS"))
            initializeEventsPanel(Objects.requireNonNull(DBConnector.getAllFinishedEvents()));
        if (eventsSortType.equals("FINISHED") && eventsType.equals("MY_EVENTS"))
            initializeEventsPanel(Objects.requireNonNull(DBConnector.getFinishedJoinedEvents(vol.getVolId())));
    }

    private ArrayList<Event> getListOfEvents() {
        if (eventsSortType.equals("ONGOING") && eventsType.equals("ALL_EVENTS"))
            return (Objects.requireNonNull(DBConnector.getAllOngoingEvents()));
        if (eventsSortType.equals("ONGOING") && eventsType.equals("MY_EVENTS"))
            return (Objects.requireNonNull(DBConnector.getOngoingJoinedEvents(vol.getVolId())));
        if (eventsSortType.equals("FINISHED") && eventsType.equals("ALL_EVENTS"))
            return (Objects.requireNonNull(DBConnector.getAllFinishedEvents()));
        if (eventsSortType.equals("FINISHED") && eventsType.equals("MY_EVENTS"))
            return (Objects.requireNonNull(DBConnector.getFinishedJoinedEvents(vol.getVolId())));
        return null;
    }

    @FXML
    void onFinishedToggle(MouseEvent event) {
        finishedEvents_toggle.setStyle("-fx-text-fill: #0BB180");
        finishedEvents_toggle.setText("Finished");
        ongoingEvents_toggle.setStyle("-fx-text-fill: #9DDCC3");
        ongoingEvents_toggle.setText("Ongoing");
        eventsSortType = "FINISHED";
        updateEventsPanel();
    }

    @FXML
    void onJoinEvent(ActionEvent event) {
        // check if user is already part of event
        if (DBConnector.isVolParticipating(vol.getVolId(), selectedSched.getSchedId())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You are already participating in this event's schedule");
            alert.showAndWait();
            return;
        }

        if (!checkAvailability(vol.getVolId(), selectedSched.getStart(), selectedSched.getEnd())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You already joined a schedule that overlaps this schedule");
            alert.showAndWait();
            return;
        }

        int isVerified = 1;
        if (selectedRole.isNeedsVerification())
            isVerified = 0;
        if (DBConnector.joinVolunteerToSchedule(vol.getVolId(), selectedSched.getSchedId(),
                selectedRole.getRoleId(), isVerified)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (isVerified == 1)
                alert.setContentText("You have successfully joined the event schedule");
            else
                alert.setContentText("Please wait for the verification of your join request");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Join Failed: Internal error in joining the event");
            alert.showAndWait();
        }


    }

    @FXML
    void onLogout(MouseEvent event) {
        // TODO: Are you sure panel
        System.exit(0);
    }

    @FXML
    void onOngoingToggle(MouseEvent event) {
        ongoingEvents_toggle.setStyle("-fx-text-fill: #0BB180");
        ongoingEvents_toggle.setText("Ongoing");
        finishedEvents_toggle.setStyle("-fx-text-fill: #9DDCC3");
        finishedEvents_toggle.setText("Finished");
        eventsSortType = "ONGOING";
        updateEventsPanel();
    }

    @FXML
    void onRolesChange(ActionEvent event) {
        try {
            selectedRole = roles_comboBox.getSelectionModel().getSelectedItem();
            System.out.println(selectedRole);
            rolesDesc_label.setText(selectedRole.getDescription());
            String program = "None";
            if (!selectedRole.getDegreeProgram().equals(""))
                program = selectedRole.getDegreeProgram();
            programReq_label.setText("Degree Program: " + program);
            if (selectedRole.getYear() != 0)
                yearReq_label.setText("Year: " + selectedRole.getYear());
            else
                yearReq_label.setText("Year: " + "None");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onScheduleChange(ActionEvent event) {
        try {
            selectedSched = sched_comboBox.getSelectionModel().getSelectedItem();
            roles_comboBox.setItems(FXCollections.observableList(selectedSched.getRoles()));
            roles_comboBox.getSelectionModel().selectFirst();

            location_label.setText(selectedSched.getLocation());
            startDateTime_label.setText(selectedSched.getStart().toString());
            endDateTime_label.setText(selectedSched.getEnd().toString());

            loadVolunteers(selectedSched.getParticipants(), selectedSched.getSchedId());
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchKeyPress(KeyEvent event) {
        if (eventSearch_field.getText().trim().isBlank())
            updateEventsPanel();
        ArrayList<Event> events = new ArrayList<>();
        String search = eventSearch_field.getText().trim().toLowerCase(Locale.ROOT);
        for (Event e : getListOfEvents()) {
            String name = e.getName().toLowerCase(Locale.ROOT);
            if (name.startsWith(search))
                events.add(e);
        }
        initializeEventsPanel(events);
    }

    private void loadVolunteers(ArrayList<String> volunteers, int schedId) {
//        ArrayList<String> volunteers = DBConnector.getVolunteers(schedId);
        int division = Math.round(((float)volunteers.size()/3));
        System.out.println(division + " " + volunteers.size());

        vboxVol1.getChildren().clear();
        vboxVol2.getChildren().clear();
        vboxVol3.getChildren().clear();

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

    private void initializeEventsPanel(ArrayList<Event> events) {
        events_vbox.getChildren().clear();
        assert events != null;
        try {
            Platform.runLater(() -> events_vbox.getChildren().clear());
            for (Event e : events) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/AllEventsCard.fxml"));
                Node node = loader.load();

                Timestamp ts = DBConnector.getUpcomingDate(e.getEvent_id());
                String date = "Event Finished";
                if (ts != null)
                    date = ts.toString();

                    // set onMouseClick
                node.setOnMouseClicked((event) -> {
                    e.setSchedules(DBConnector.getEventSchedules(e.getEvent_id()));
                    setRightCardProperties(e);
                    selectedEvent = e;

                    join_button.setDisable(!eventsSortType.equals("ONGOING"));
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

    private void setRightCardProperties(Event e) {
        eventName_label.setText(e.getName());
        eventDesc_label.setText(e.getDescription());
        sched_comboBox.setItems(FXCollections.observableList(e.getSchedules()));
        sched_comboBox.getSelectionModel().selectFirst();
        selectedSched = sched_comboBox.getSelectionModel().getSelectedItem();
        roles_comboBox.setItems(FXCollections.observableList(
                sched_comboBox.getSelectionModel().getSelectedItem().getRoles()));
        roles_comboBox.getSelectionModel().selectFirst();
        selectedRole = roles_comboBox.getSelectionModel().getSelectedItem();

        List<Map<String, String>> list = DBConnector.getVolunteerSchedRole(vol.getVolId(), e.getEvent_id());
        String label = "You are not participating in any schedule";
        if (list.size() == 0)
            userRole_label.setText(label);
        else
            label = "";
        for (Map<String, String> m : list) {
            label += "schedule " + m.get("schedId") + " - " + m.get("role") + "\n";
        }
        userRole_label.setText(label);
    }

    protected boolean checkAvailability(int volId, Timestamp start, Timestamp end) {
        Map<Timestamp, Timestamp> volScheds = DBConnector.getVolunteerSchedules(volId);

        boolean isAvailable = true;
        for (Map.Entry<Timestamp, Timestamp> entry : volScheds.entrySet()) {
            Timestamp eventStart = entry.getKey();
            Timestamp eventEnd = entry.getValue();

            if (eventStart.compareTo(start) > 0 && start.compareTo(eventEnd) < 0) {
                isAvailable = false;
            } else if (eventStart.compareTo(end) > 0 && end.compareTo(eventEnd) < 0) {
                isAvailable = false;
            }
        }

        return isAvailable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateEventsPanel();
        name_label.setText(vol.getFirstName() + " " + vol.getLastName());

        ongoingEvents_toggle.setStyle("-fx-text-fill: #0BB180");
        ongoingEvents_toggle.setText("Ongoing");
        finishedEvents_toggle.setStyle("-fx-text-fill: #9DDCC3");
        finishedEvents_toggle.setText("Finished");
    }

}
