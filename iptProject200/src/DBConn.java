
import java.sql.*;

public class DBConn {

    String DB_URL = "jdbc:sqlite:demo.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String LogInn(String Username, String Password) {
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, Username);
            pstmt.setString(2, Password);

            ResultSet rs = pstmt.executeQuery();

            String role = rs.getString("role");

            return rs.next() ? role: "";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
