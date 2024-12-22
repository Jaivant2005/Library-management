 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Database URL, Username, and Password
    private static final String URL = "jdbc:mysql://localhost:3306/wad"; // Replace 'wad' with your database name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "2005"; // Replace with your MySQL password
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // MySQL JDBC Driver

    // Static block to load the JDBC driver
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC Driver: " + e.getMessage());
        }
    }

    // Method to establish a connection to the database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
        return conn;
    }

    // Method to close the database connection
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
