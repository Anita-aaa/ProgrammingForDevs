import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/social_media_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234567";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_db", "root", "1234567");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to MySQL database.");
            } else {
                System.out.println("Failed to connect to MySQL database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
