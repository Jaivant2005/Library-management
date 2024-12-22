import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/library";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "2005";

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to get a user by email
    public User getUser(String email) throws SQLException {
        String query = "SELECT email, password FROM users WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // Check if a user with the given email exists
            if (rs.next()) {
               // int id = rs.getInt("id");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");

                // Return the user object
                return new User( userEmail, userPassword);
            } else {
                // No user found with the given email
                return null;
            }
        }
    }
}
