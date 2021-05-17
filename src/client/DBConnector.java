package client;

import models.Concern;
import models.Event;
import models.Volunteer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    public static ArrayList<Event> getAllOngoingEvents() {
//        ArrayList<Event> events = new ArrayList<>();
//        try {
//            PreparedStatement statement = DBConnector.con.prepareStatement(
//                    ""
//            );
//
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static ArrayList<Event> getAllFinishedEvents() {
//
//    }

    public static ArrayList<Event> getOngoingJoinedEvents(int volId) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = DBConnector.con.prepareStatement(
                    "SELECT\n" +
                            "    *\n" +
                            "FROM EVENT\n" +
                            "WHERE\n" +
                            "    event_id IN(\n" +
                            "    SELECT DISTINCT\n" +
                            "        event_schedule.event_id\n" +
                            "    FROM\n" +
                            "        event_schedule\n" +
                            "    LEFT JOIN volunteer_event_list USING(sched_id)\n" +
                            "    WHERE\n" +
                            "        dateTime_end > CURRENT_TIMESTAMP AND vol_id = ?" +
                            ")"
            );
            statement.setInt(1, volId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Event e = new Event(rs.getInt(1), rs.getString(2), rs.getString(3));
                events.add(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return events;
    }

    public static ArrayList<Event> getAllOngoingEvents(int volId) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = DBConnector.con.prepareStatement(
                    "SELECT\n" +
                            "    *\n" +
                            "FROM EVENT\n" +
                            "WHERE\n" +
                            "    event_id IN(\n" +
                            "    SELECT DISTINCT\n" +
                            "        event_schedule.event_id\n" +
                            "    FROM\n" +
                            "        event_schedule\n" +
                            "    LEFT JOIN volunteer_event_list USING(sched_id)\n" +
                            "    WHERE\n" +
                            "        dateTime_end > CURRENT_TIMESTAMP \n" +
                            ")"
            );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Event e = new Event(rs.getInt(1), rs.getString(2), rs.getString(3));
                events.add(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return events;
    }

//    public static ArrayList<Event> getFinishedJoinedEvents(int volId) {
//        ArrayList<Event> events = new ArrayList<>();
//        try {
//            PreparedStatement statement = DBConnector.con.prepareStatement(
//                    "SELECT\n" +
//                            "    *\n" +
//                            "FROM EVENT\n" +
//                            "WHERE\n" +
//                            "    event_id IN(\n" +
//                            "    SELECT DISTINCT\n" +
//                            "        event_schedule.event_id\n" +
//                            "    FROM\n" +
//                            "        event_schedule\n" +
//                            "    LEFT JOIN volunteer_event_list USING(sched_id)\n" +
//                            "    WHERE\n" +
//                            "        dateTime_end > CURRENT_TIMESTAMP AND vol_id = ?" +
//                            ")"
//            );
//            statement.setInt(1, volId);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Event e = new Event(rs.getInt(1), rs.getString(2), rs.getString(3));
//                events.add(e);
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return events;
//    }


    /**
     *
     * @return
     * -2 - error in login in
     * -1 - username is non existent
     * 0 - username-password combination does not match
     * 1 - successful volunteer log in
     * 2 - successful admin log in
     */
    public static int login(String username, String password) {
        try {
            PreparedStatement statement = DBConnector.con.prepareStatement(
                    "SELECT * FROM user_acc WHERE username = ?"
            );
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    if (rs.getString("type").equals("volunteer"))
                        return 1; // volunteer log in
                    else
                        return 2; // admin log in
                }else // username exists but incorrect pass
                    return 0;
            }else {
                return -1; // username does not exist
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return -2; // error occured while loggin in
        }
    }

    public static boolean usernameIsAvailable(String username) {
        try {
            PreparedStatement statement = DBConnector.con.prepareStatement(
                    "SELECT user_id FROM user_acc WHERE username = ?"
            );
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return !rs.next();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean register(Volunteer volunteer) {
        try {
            // Insert into user_acc table
            PreparedStatement userStatement = DBConnector.con.prepareStatement(
                    "INSERT INTO user_acc(username, PASSWORD, TYPE) VALUES(?, ?, ?)");
            userStatement.setString(1, volunteer.getUsername());
            userStatement.setString(2, volunteer.getPassword());
            userStatement.setString(3, volunteer.getUserType());
            userStatement.executeUpdate();
            // Retrieve ID for FK
            PreparedStatement idStatement = new DBConnector().con.prepareStatement(
                    "SELECT user_id from user_acc where username = ?"
            );
            idStatement.setString(1,volunteer.getUsername());
            ResultSet rs = idStatement.executeQuery();
            rs.next();
            int user_id = rs.getInt(1);
            // Insert into volunteer_info table
            PreparedStatement volStatement = DBConnector.con.prepareStatement(
                    "INSERT INTO volunteer_info (first_name, last_name, birth_date, address, phone_number, type, year, degree_program, sex, user_id)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            volStatement.setString(1, volunteer.getFirstName());
            volStatement.setString(2, volunteer.getLastName());
            volStatement.setDate(3, volunteer.getBirthDate());
            volStatement.setString(4, volunteer.getAddress());
            volStatement.setLong(5, volunteer.getPhone_number());
            volStatement.setString(6, volunteer.getVolType().toString());
            volStatement.setInt(7, volunteer.getYear());
            volStatement.setString(8, volunteer.getDegreeProgram());
            volStatement.setString(9, volunteer.getSex().toString());
            volStatement.setInt(10, user_id);
            volStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
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
//    public static List<Event> getEventsOfVolunteer(Volunteer v, int type) {
//        List<Event> events = new ArrayList<>();
//        try {
//            String query = "SELECT\n" +
//                    "    e.name e_name,\n" +
//                    "    e.event_id e_id,\n" +
//                    "    e.description e_desc,\n" +
//                    "    s.dateTime_start s_start,\n" +
//                    "    s.dateTime_end s_end,\n" +
//                    "    s.location s_location,\n" +
//                    "    s.vol_limit s_limit,\n" +
//                    "    s.sched_id s_id\n" +
//                    "FROM\n" +
//                    "    volunteer_event_list AS v\n" +
//                    "INNER JOIN events_schedule AS s USING(sched_id)\n" +
//                    "NATURAL JOIN EVENT AS e \n" +
//                    "WHERE\n" +
//                    "    v.user_id = ?";
//            PreparedStatement s = null;
//            if (type == 2) {
//                s = con.prepareStatement(query);
//            }else if (type == 1 | type == 0) {
//                query = query + " AND v.is_verified = ?";
//                s = con.prepareStatement(query);
//                s.setInt(2, type);
//            }else {
//                throw new IllegalArgumentException();
//            }
////            s.setInt(1, v.getId());
//            ResultSet rs = s.executeQuery();
//            // For ever tuple
//            while (rs.next()) {
//                int eventID = rs.getInt(2);
//                // If event does not exist create the event
//                if (!events.contains(new Event(eventID))) {
//                    // Get list of roles for event
//                    PreparedStatement rolesStatement = con.prepareStatement(
//                      "SELECT * FROM EVENT_ROLES WHERE event_id = ?"
//                    );
//                    rolesStatement.setInt(1, eventID);
//                    ResultSet rolesSet = rolesStatement.executeQuery();
//                    List<Role> roles = new ArrayList<>();
//                    while (rolesSet.next()) {
//                        roles.add(new Role(rolesSet.getString(1), rolesSet.getString(2)));
//                    }
//                    // Add new event to list of events
//                    List<EventSchedule> es = new ArrayList<>();
//                    events.add(new Event(rs.getInt(2), rs.getString(1),
//                            rs.getString(3), es, roles));
//                }
//                // Create event schedule
//                EventSchedule es = new EventSchedule(rs.getTimestamp(4),
//                        rs.getTimestamp(5), rs.getString(6),
//                        rs.getInt(7), rs.getInt(8));
//                // Add event schedule to list of schedules for appropriate event
//                for (Event e : events) {
//                    if (e.equals(new Event(eventID))) {
//                        e.addSchedule(es);
//                        break;
//                    }
//                }
//            }
//        }catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//        return events;
//    }

    /**
     * Inserts a new concern to the volunter_concerns table given a concern to be inserted.
     */
    public static boolean insertConcern(Concern concern){

        String query = "INSERT INTO volunteer_concerns (type, description, vol_id, sched_id)" +
                "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);

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

    public static void getFinishedEvents(int volId) {
        try {
            PreparedStatement getFinished = DBConnector.con.prepareStatement("" +
                    "SELECT *\n" +
                    "FROM event\n" +
                    "WHERE event.event_id=ALL (\n" +
                    "    SELECT es.event_id \n" +
                    "    FROM event_schedule AS es \n" +
                    "    WHERE es.sched_id IN ( \n" +
                    "        SELECT vel.sched_id \n" +
                    "        FROM volunteer_event_list AS vel \n" +
                    "        WHERE vel.vol_id=? \n" +
                    "    ) AND es.dateTime_end < NOW()\n" +
                    ")"
            );

            getFinished.setInt(1, volId);

            ResultSet finishedEvents = getFinished.executeQuery();

            Map<String, String> eventMap = new HashMap<>();
            while (finishedEvents.next()) {
                eventMap.put("event_id", String.valueOf(finishedEvents.getInt(1)));
                eventMap.put("name",finishedEvents.getString(2));
                eventMap.put("description",finishedEvents.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<String> getVolunteers(int volId) {
        List<String> volList = new ArrayList<>();
        try {
            PreparedStatement getVol = DBConnector.con.prepareStatement(
                    "SELECT info.first_name, info.last_name\n" +
                            "FROM volunteer_info as info\n" +
                            "LEFT JOIN (\n" +
                            "    SELECT vel.vol_id\n" +
                            "    FROM volunteer_event_list AS vel\n" +
                            "    WHERE vel.sched_id=?    \n" +
                            ") as invel ON info.vol_id=invel.vol_id;"
            );

            getVol.setInt(1, volId);

            ResultSet vols = getVol.executeQuery();

            while (vols.next()) {
                volList.add(vols.getString("first_name") + " " + vols.getString("last_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return volList;
    }
    
}
