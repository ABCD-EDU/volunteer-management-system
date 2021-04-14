import java.sql.*;

/**
 * Create a mysql user with username:221-midexam, password:221-midexam
 * and grant all privileges.
 *
 *
 * HOW TO SETUP: in 222-preGrp3 README
 */
public class DBConnector {
    public static Connection createConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost/221-events?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false"
                    ,"221-midexam", "221-midexam");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("DATABASE CONNECTION FAILED");
        }
    }
}
