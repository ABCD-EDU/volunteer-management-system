package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Concern;

public class ClientExecutable extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/SignInScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Textra");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            DBConnector.setConnection();

            launch(args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
////            byte b = 123;
//            DBConnector.setConnection();
////            List<Event> events = DBConnector.getEventsOfVolunteer(
////                    new Volunteer(2201029, "hatdog", User.Type.VOLUNTEER, "hatdog", "hatdog", b, "hatdog", 2201017, new PersonalInfo())
////                    , 2);
////            System.out.println("events: " + events.size());
////            for (Event e : events) {
////                System.out.println(e.getName() + " - schedules: " + e.getSchedule().size());
////            }
//
////            DBConnector.insertConcern("inquiry", "How much is one potato?", 3,5);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
