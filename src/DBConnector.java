import models.Event;
import models.Volunteer;

import java.security.InvalidAlgorithmParameterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a mysql user with username:221-midexam, password:221-midexam
 * and grant all privileges.
 *
 *
 * HOW TO SETUP: in 222-preGrp3 README
 */
public class DBConnector {

    private static final String DATABASE_NAME = "221-events";
    private static Connection con;
    public DBConnector() { }

    public static void setConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE_NAME + "?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false"
                    ,"root", "");
            System.out.println("DATABASE CONNECTION SUCCESSFUL");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of events a volunteer is part of. The list of events returned may contain different different
     * values depending on the inputted value of parameter returnType.
     * TODO: finish
     * Algorithm:
     * 1. Get list of scheduled events volunteer is part of
     * 2.
     * @param returnType an inputted value of:
     *                   0 - will return events a volunteer is volunteering for but is not yet verified to join
     *                   1 - will return events a volunteer is already verified to join
     *                   2 - will return all events a volunteer is part of
     */
    public static List<Event> getEventsOfVolunteer(Volunteer volunteer, int returnType) {
        List<Event> events = new ArrayList<>();
//        try {
//            PreparedStatement s = null;
//            if (returnType == 2) {
//                s = con.prepareStatement
//                        ("SELECT * FROM volunteer_event_list WHERE user_id=?");
//            }else if (returnType == 1 | returnType == 0) {
//                s = con.prepareStatement
//                        ("SELECT * FROM volunteer_event_list WHERE user_id=? AND is_verified=?");
//                s.setInt(2, returnType);
//            }else {
//                throw new IllegalArgumentException();
//            }
//            s.setInt(1, volunteer.getUserID());
//            ResultSet rs = s.executeQuery();
//        }catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
        return events;
    }

//    private List<Event>

    public static void closeConnection() throws Exception {
        if (con != null) {
            con.close();
        }
    }

}
