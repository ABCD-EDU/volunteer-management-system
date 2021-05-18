package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EventCardController implements Initializable {

    @FXML
    private Pane event_card;

    @FXML
    private Label name_label;

    @FXML
    private Label date_label;

    @FXML
    void toBlueCard() {
        event_card.setStyle("-fx-background-color: #227C9D; -fx-background-radius: 10");
        name_label.setStyle("-fx-text-fill: #FFFFFF");
        date_label.setStyle("-fx-text-fill: #FFFFFF");
    }

    @FXML
    void toOrangeCard() {
        event_card.setStyle("-fx-background-color:  #FFCB77; -fx-background-radius: 10");
        name_label.setStyle("-fx-text-fill: #444444");
        date_label.setStyle("-fx-text-fill: #444444");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
