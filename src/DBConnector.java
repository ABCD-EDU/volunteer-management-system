import models.Event;
import models.EventSchedule;
import models.Role;
import models.Volunteer;

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
     * TODO: clean
     * Algorithm:
     * 1. Get list of scheduled events the volunteer is part of. Include the event's information
     * 2. Get role of volunteer for each scheduled event and assign them respectively.
     * 3
     * @param returnType an inputted value of:
     *                   0 - will return events a volunteer is volunteering for but is not yet verified to join
     *                   1 - will return events a volunteer is already verified to join
     *                   2 - will return all events a volunteer is part of
     */
    public static List<Event> getEventsOfVolunteer(Volunteer volunteer, int returnType) {
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
            if (returnType == 2) {
                s = con.prepareStatement(query);
            }else if (returnType == 1 | returnType == 0) {
                query = query + " AND v.is_verified = ?";
                s = con.prepareStatement(query);
                s.setInt(2, returnType);
            }else {
                throw new IllegalArgumentException();
            }
            s.setInt(1, volunteer.getId());
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                int eventID = rs.getInt(2);
                if (!events.contains(new Event(eventID))) {
                    List<EventSchedule> es = new ArrayList<>();
                    events.add(new Event(rs.getInt(2), rs.getString(1), rs.getString(3), es));
                }
                EventSchedule es = new EventSchedule(rs.getTimestamp(4),
                        rs.getTimestamp(5), rs.getString(6),
                        rs.getInt(7), new Role(rs.getString(9)), rs.getInt(8));
                for (Event e : events) {
                    if (e.equals(new Event(eventID))) {
                        e.addSchedule(es);
                        break;
                    }
                }
                System.out.println("ename - " + rs.getString("e_name"));
                System.out.println("eid - " + rs.getInt(2));
                System.out.println("edesc - " + rs.getString(3));
                System.out.println("start - " + rs.getTimestamp(4));
                System.out.println("end - " + rs.getTimestamp(5));
                System.out.println("location - " + rs.getString(6));
                System.out.println("limit - " + rs.getInt(7));
                System.out.println("schedID - " + rs.getInt(8));
                System.out.println("rname - " + rs.getString(9));
            }
        }catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return events;
    }

//    private List<Event>

    public static void closeConnection() throws Exception {
        if (con != null) {
            con.close();
        }
    }

}
