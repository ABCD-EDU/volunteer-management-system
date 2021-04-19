import models.*;

import java.security.InvalidAlgorithmParameterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            Class.forName("com.mysql.jdbc.Driver");
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
     *
     * Algorithm:
     * 1. Get list of scheduled events the volunteer is part of
     * 2. For every scheduled event:
     *  2.1 If the event in which the schedule is for does not yet exist within the list of events to be returned,
     *      create an event object and add it into the list.
     *  2.2 Create a scheduleEvent object and add it into its appropriate event
     * 3. Return the list
     * @param type an inputted value of:
     *                   0 - will return events a volunteer is volunteering for but is not yet verified to join
     *                   1 - will return events a volunteer is already verified to join
     *                   2 - will return all events a volunteer is part of
     */
    public static List<Event> getEventsOfVolunteer(Volunteer v, int type) {
        List<Event> events = new ArrayList<>();
        try {
            String query = "SELECT\n" +
                    "    e.name e_name,\n" +
                    "    e.event_id e_id,\n" +
                    "    e.description e_desc,\n" +
                    "    s.dateTime_start s_start,\n" +
                    "    s.dateTime_end s_end,\n" +
                    "    s.location s_location,\n" +
                    "    s.vol_limit s_limit,\n" +
                    "    s.sched_id s_id,\n" +
                    "    r.name r_name\n" +
                    "FROM\n" +
                    "    volunteer_event_list AS v\n" +
                    "INNER JOIN events_schedule AS s USING(sched_id)\n" +
                    "NATURAL JOIN EVENT AS e\n" +
                    "INNER JOIN event_roles AS r USING(event_role_id)\n" +
                    "WHERE\n" +
                    "    v.user_id = ?";
            PreparedStatement s = null;
            if (type == 2) {
                s = con.prepareStatement(query);
            }else if (type == 1 | type == 0) {
                query = query + " AND v.is_verified = ?";
                s = con.prepareStatement(query);
                s.setInt(2, type);
            }else {
                throw new IllegalArgumentException();
            }
            s.setInt(1, v.getId());
            ResultSet rs = s.executeQuery();
            // For ever tuple
            while (rs.next()) {
                int eventID = rs.getInt(2);
                if (!events.contains(new Event(eventID))) {
                    // If event does not exist create the event
                    List<EventSchedule> es = new ArrayList<>();
                    events.add(new Event(rs.getInt(2), rs.getString(1), rs.getString(3), es));
                }
                // Create event schedule
                EventSchedule es = new EventSchedule(rs.getTimestamp(4),
                        rs.getTimestamp(5), rs.getString(6),
                        rs.getInt(7), new Role(rs.getString(9)), rs.getInt(8));
                // Add event schedule to list of schedules for appropriate event
                for (Event e : events) {
                    if (e.equals(new Event(eventID))) {
                        e.addSchedule(es);
                        break;
                    }
                }
            }
        }catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static boolean insertConcern(Concern concern){

        String query = "INSERT INTO volunteer_concerns (type, concern_description, user_id, event_id)" +
                "VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = DBConnector.con.prepareStatement(query)){

            statement.setString(1,concern.getType());
            statement.setString(2, concern.getDesc());
            statement.setInt(3, concern.getUserID());
            statement.setInt(4, concern.getEventID());

            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void closeConnection() throws Exception {
        if (con != null) {
            con.close();
        }
    }

}
