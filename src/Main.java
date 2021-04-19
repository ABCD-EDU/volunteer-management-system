import models.Event;
import models.PersonalInfo;
import models.User;
import models.Volunteer;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            byte b = 123;
            DBConnector.setConnection();
            List<Event> events = DBConnector.getEventsOfVolunteer(
                    new Volunteer(2201029, "hatdog", User.Type.VOLUNTEER, "hatdog", "hatdog", b, "hatdog", 2201017, new PersonalInfo())
                    , 2);
            System.out.println("events: " + events.size());
            for (Event e : events) {
                System.out.println(e.getName() + " - schedules: " + e.getSchedule().size());
            }


            DBConnector.insertConcern("inquiry", "How much is one potato?", 3,5);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
